package com.github.org.todaybread.todaybread.product.application.facade;

import com.github.org.todaybread.todaybread.file.application.facade.FileFacadeImpl;
import com.github.org.todaybread.todaybread.file.domain.File;
import com.github.org.todaybread.todaybread.file.domain.FileType;
import com.github.org.todaybread.todaybread.product.application.service.ProductServiceImpl;
import com.github.org.todaybread.todaybread.product.domain.Product;
import com.github.org.todaybread.todaybread.product.infra.http.request.CreateProductRequest;
import com.github.org.todaybread.todaybread.product.infra.http.response.ProductResponse;
import com.github.org.todaybread.todaybread.steppay.application.SteppayProductServiceImpl;
import com.github.org.todaybread.todaybread.steppay.infra.request.SteppayCreateProductRequest;
import com.github.org.todaybread.todaybread.steppay.infra.response.SteppayProductResponse;
import com.github.org.todaybread.todaybread.store.application.service.StoreServiceImpl;
import com.github.org.todaybread.todaybread.store.domain.Store;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ProductFacadeImpl implements ProductFacade {

    private final FileFacadeImpl fileFacade;

    private final ProductServiceImpl productService;
    private final StoreServiceImpl storeService;
    private final SteppayProductServiceImpl steppayProductService;

    @Override
    public ProductResponse create(String memberId, CreateProductRequest request) {
        Store store = storeService.getByManagerId(request.getManagerId());

        File image = fileFacade.upload(
            request.getManagerId(),
            FileType.PACKAGE,
            request.getImage()
        );

        SteppayProductResponse response = steppayProductService.create(
            SteppayCreateProductRequest.builder()
                .name(request.getName())
                .featuredImageUrl(image.toResponse().getUrl())
                .description(request.getDescription())
                .build()
        );

        Product product = productService.save(
            Product.builder()
                .id(response.getId())
                .createdAt(response.getCreatedAt())
                .updatedAt(response.getModifiedAt())
                .store(store)
                .featureImage(image)
                .name(response.getName())
                .description(response.getDescription())
                .breadType(request.getBreadType())
                .price(request.getPrice())
                .build()
        );

        return product.toResponse();
    }
}
