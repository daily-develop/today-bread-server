package com.github.org.todaybread.todaybread.product.infra.http;

import com.github.org.todaybread.todaybread.product.application.facade.ProductFacade;
import com.github.org.todaybread.todaybread.product.domain.BreadType;
import com.github.org.todaybread.todaybread.product.infra.http.request.CreateProductRequest;
import com.github.org.todaybread.todaybread.product.infra.http.request.UpdateProductRequest;
import com.github.org.todaybread.todaybread.product.infra.http.response.ProductResponse;
import java.util.List;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
@PreAuthorize(value = "hasAuthority('GENERAL')")
public class ProductController {

    private final ProductFacade productFacade;

    @QueryMapping
    public ProductResponse product(
        @Argument String productId
    ) {
        return productFacade.getById(productId);
    }

    @QueryMapping
    public List<ProductResponse> products(
        @Argument String storeId,
        @Argument BreadType breadType,
        @Valid @Min(1) @Argument int page,
        @Valid @Min(1) @Argument int take
    ) {
        return productFacade.getList(storeId, breadType, page, take);
    }

    @QueryMapping
    public List<ProductResponse> recommendedProducts(
        @Valid @Min(1) @Argument int take
    ) {
        return productFacade.getRecommended(take);
    }

    @QueryMapping
    public List<ProductResponse> productsRanking(
        @Argument int page,
        @Argument int take
    ) {
        return productFacade.getRanking(page, take);
    }

    @MutationMapping
    public ProductResponse createProduct(
        Authentication authentication,
        @Valid @Argument CreateProductRequest request
    ) {
        return productFacade.create(authentication.getName(), request);
    }

    @MutationMapping
    public ProductResponse updateProduct(
        Authentication authentication,
        @Valid @Argument UpdateProductRequest request
    ) {
        return productFacade.update(authentication.getName(), request);
    }

    @MutationMapping
    public ProductResponse stopProduct(
        Authentication authentication,
        @Argument String productId
    ) {
        return productFacade.stop(authentication.getName(), productId);
    }
}
