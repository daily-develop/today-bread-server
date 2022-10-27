package com.github.org.todaybread.todaybread.product.application.facade;

import com.github.org.todaybread.todaybread.file.application.facade.FileFacade;
import com.github.org.todaybread.todaybread.file.domain.File;
import com.github.org.todaybread.todaybread.file.domain.FileType;
import com.github.org.todaybread.todaybread.product.application.service.ProductService;
import com.github.org.todaybread.todaybread.product.domain.Product;
import com.github.org.todaybread.todaybread.product.infra.http.request.CreateProductRequest;
import com.github.org.todaybread.todaybread.product.infra.http.response.ProductResponse;
import com.github.org.todaybread.todaybread.steppay.product.application.SteppayProductService;
import com.github.org.todaybread.todaybread.steppay.product.infra.request.SteppayCreateProductRequest;
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
    private final StoreService storeService;
    private final SteppayProductService steppayProductService;

    @Override
    public ProductResponse getById(String productId) {
        return productService.getById(productId).toResponse();
    }

    @Override
    public List<ProductResponse> getList(String storeId, int page, int take, String search) {
        return productService.getList(storeId, page, take, search).stream()
            .map(Product::toResponse)
            .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public ProductResponse create(String memberId, CreateProductRequest request) {
        Store store = storeService.getById(request.getStoreId());

        File image = null;
        if (request.getImage() != null) {
            image = fileFacade.upload(
                store.getManager().getMember().getId().toString(),
                FileType.PACKAGE,
                request.getImage()
            );
        }

        SteppayProductResponse response = steppayProductService.create(
            SteppayCreateProductRequest.builder()
                .name(request.getName())
                .featuredImageUrl(image != null ? image.toResponse().getUrl() : "")
                .description(request.getDescription())
                .build()
        );

        Product product = productService.save(
            Product.builder()
                .steppayId(response.getId())
                .store(store)
                .featureImage(image)
                .name(response.getName())
                .description(response.getDescription())
                .breadType(request.getBreadType())
                .price(request.getPrice())
                .quantity(request.getQuantity())
                .build()
        );

        return product.toResponse();
    }
}
