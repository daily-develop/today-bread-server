package com.github.org.todaybread.todaybread.product.application.facade;

import com.github.org.todaybread.todaybread.file.application.facade.FileFacade;
import com.github.org.todaybread.todaybread.file.domain.File;
import com.github.org.todaybread.todaybread.file.domain.FileType;
import com.github.org.todaybread.todaybread.manager.exception.NotFoundManagerException;
import com.github.org.todaybread.todaybread.manager.exception.NotManagerException;
import com.github.org.todaybread.todaybread.product.application.service.ProductService;
import com.github.org.todaybread.todaybread.product.attachment.application.ProductAttachmentService;
import com.github.org.todaybread.todaybread.product.attachment.domain.ProductAttachment;
import com.github.org.todaybread.todaybread.product.domain.BreadType;
import com.github.org.todaybread.todaybread.product.domain.Product;
import com.github.org.todaybread.todaybread.product.infra.http.request.CreateProductRequest;
import com.github.org.todaybread.todaybread.product.infra.http.request.UpdateProductRequest;
import com.github.org.todaybread.todaybread.product.infra.http.response.ProductResponse;
import com.github.org.todaybread.todaybread.steppay.plan.application.SteppayPlanService;
import com.github.org.todaybread.todaybread.steppay.plan.domain.SteppayPlanInfo;
import com.github.org.todaybread.todaybread.steppay.plan.infra.request.SteppayCreatePlanRequest;
import com.github.org.todaybread.todaybread.steppay.plan.infra.response.SteppayPlanResponse;
import com.github.org.todaybread.todaybread.steppay.product.application.SteppayProductService;
import com.github.org.todaybread.todaybread.steppay.product.infra.request.SteppayCreateProductRequest;
import com.github.org.todaybread.todaybread.steppay.product.infra.request.SteppayUpdateProductRequest;
import com.github.org.todaybread.todaybread.steppay.product.infra.response.SteppayProductResponse;
import com.github.org.todaybread.todaybread.store.application.service.StoreService;
import com.github.org.todaybread.todaybread.store.domain.Store;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ProductFacadeImpl implements ProductFacade {

    private final FileFacade fileFacade;

    private final ProductService productService;
    private final ProductAttachmentService productAttachmentService;
    private final StoreService storeService;
    private final SteppayProductService steppayProductService;
    private final SteppayPlanService steppayPlanService;

    @Override
    public ProductResponse getById(String productId) {
        return productService.getById(productId).toResponse();
    }

    @Override
    public List<ProductResponse> getList(String storeId, BreadType breadType, int page, int take) {
        return productService.getList(storeId, breadType, page, take).stream()
            .map(Product::toResponse)
            .collect(Collectors.toList());
    }

    @Override
    public List<ProductResponse> getRecommended(int take) {
        return productService.getRecommended(take).stream()
            .map(Product::toResponse)
            .collect(Collectors.toList());
    }

    @Override
    public List<ProductResponse> getRanking(int page, int take) {
        return productService.getRanking(page, take).stream()
            .map(Product::toResponse)
            .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public ProductResponse create(String memberId, CreateProductRequest request) {
        Store store = storeService.getById(request.getStoreId());
        if (!store.getManager().getMember().getId().toString().equals(memberId)) {
            throw new NotManagerException();
        }

        File image = null;
        if (request.getImage() != null) {
            image = fileFacade.upload(
                store.getManager().getMember().getId().toString(),
                FileType.PRODUCT,
                request.getImage()
            );
        }

        SteppayProductResponse productResponse = steppayProductService.create(
            SteppayCreateProductRequest.builder()
                .name(request.getName())
                .featuredImageUrl(image != null ? image.toResponse().getUrl() : "")
                .quantity(request.getQuantity())
                .build()
        );

        SteppayPlanResponse planResponse = steppayPlanService.create(
            productResponse.getId(),
            SteppayCreatePlanRequest.builder()
                .price(request.getPrice())
                .plan(
                    SteppayPlanInfo.builder()
                        .name(request.getName())
                        .description(store.getName())
                        .build()
                )
                .build()
        );

        Product product = productService.save(
            Product.builder()
                .steppayId(productResponse.getId())
                .steppayCode(productResponse.getCode())
                .store(store)
                .featureImage(image)
                .name(productResponse.getName())
                .breadType(request.getBreadType())
                .price(planResponse.getPrice())
                .steppayPlanCode(planResponse.getCode())
                .quantity(productResponse.getQuantity())
                .build()
        );

        product.updateDescription(
            request.getDescription()
                .stream()
                .map(it -> productAttachmentService.save(memberId, product, it))
                .collect(Collectors.toList())
        );

        return product.toResponse();
    }

    @Override
    @Transactional
    public ProductResponse update(String memberId, UpdateProductRequest request) {
        Product product = productService.getById(request.getProductId());
        if (!product.getStore().getManager().getMember().getId().toString().equals(memberId)) {
            throw new NotFoundManagerException();
        }

        File file = fileFacade.getById(request.getImageId());

        SteppayProductResponse response = steppayProductService.update(
            product.getSteppayId(),
            SteppayUpdateProductRequest.builder()
                .name(request.getName())
                .featuredImageUrl(file != null ? file.toResponse().getUrl() : "")
                .quantity(request.getQuantity())
                .build()
        );

        product
            .updateImage(file)
            .updateName(response.getName())
            .updateBreadType(request.getBreadType())
            .updatePrice(request.getPrice())
            .updateQuantity(response.getQuantity());

        product.updateDescription(
            request.getDescription()
                .stream()
                .map(it -> {
                    ProductAttachment attachment = productAttachmentService.getByFileId(it);
                    if (attachment == null) {
                        attachment = productAttachmentService.saveByFileId(product, it);
                    }
                    return attachment;
                })
                .toList()
        );

        return product.toResponse();
    }

    @Override
    @Transactional
    public ProductResponse stop(String memberId, String productId) {
        Product product = productService.getById(productId);
        if (!product.getStore().getManager().getMember().getId().toString().equals(memberId)) {
            throw new NotFoundManagerException();
        }

        steppayProductService.stop(product.getSteppayId());
        product.updateStatus(false);

        return product.toResponse();
    }
}
