package com.github.org.todaybread.todaybread.product.application.facade;

import static org.assertj.core.api.Assertions.assertThat;

import com.github.org.todaybread.todaybread.manager.infra.http.request.CreateManagerRequest;
import com.github.org.todaybread.todaybread.member.domain.Member;
import com.github.org.todaybread.todaybread.member.domain.MemberRepository;
import com.github.org.todaybread.todaybread.product.domain.BreadType;
import com.github.org.todaybread.todaybread.product.infra.http.request.CreateProductRequest;
import com.github.org.todaybread.todaybread.product.infra.http.request.UpdateProductRequest;
import com.github.org.todaybread.todaybread.product.infra.http.response.ProductResponse;
import com.github.org.todaybread.todaybread.store.application.facade.StoreFacade;
import com.github.org.todaybread.todaybread.store.infra.http.request.CreateStoreRequest;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
@Transactional
class ProductFacadeImplTest {

    private String memberId;
    private String storeId;

    @Autowired
    private ProductFacadeImpl productFacade;
    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private StoreFacade storeFacade;

    @BeforeEach
    public void beforeEach() {
        memberId = memberRepository.save(
            Member.builder()
                .steppayId(1000L)
                .name("test_name")
                .email("test@email.com")
                .phone("010-0000-0000")
                .postcode("12345")
                .address1("서울시 강남구")
                .address2("삼성동 134번지 52호")
                .build()
        ).getId().toString();

        storeId = storeFacade.create(
            memberId,
            CreateStoreRequest.builder()
                .name("test_store")
                .description("test_description")
                .location("test_location")
                .phone("010-1111-1111")
                .manager(
                    CreateManagerRequest.builder()
                        .nickname("test_manager_nickname")
                        .build()
                )
                .build()
        ).getId();
    }

    @Test
    @DisplayName("상품을 생성할 수 있어요.")
    public void createProduct() {
        ProductResponse response = productFacade.create(
            memberId,
            CreateProductRequest.builder()
                .storeId(storeId)
                .name("test_product")
                .breadType(BreadType.BREAD)
                .description(List.of())
                .price(10_000L)
                .quantity(null)
                .build()
        );

        assertThat(response).isNotNull().isInstanceOf(ProductResponse.class);

        assertThat(response.getName()).isEqualTo("test_product");
        assertThat(response.getBreadType()).isEqualTo(BreadType.BREAD);
        assertThat(response.getPrice()).isEqualTo(10000);
        assertThat(response.getQuantity()).isEqualTo(null);
    }

    @Test
    @DisplayName("상품을 조회할 수 있어요.")
    public void findProduct() {
        ProductResponse response = productFacade.create(
            memberId,
            CreateProductRequest.builder()
                .storeId(storeId)
                .name("test_product")
                .breadType(BreadType.BREAD)
                .description(List.of())
                .price(10_000L)
                .quantity(null)
                .build()
        );

        ProductResponse result = productFacade.getById(response.getId());

        assertThat(result).isNotNull().isInstanceOf(ProductResponse.class);

        assertThat(result).usingRecursiveComparison().isEqualTo(response);
    }

    @Test
    @DisplayName("상품을 수정할 수 있어요.")
    public void updateProduct() {
        ProductResponse response = productFacade.create(
            memberId,
            CreateProductRequest.builder()
                .storeId(storeId)
                .name("test_product")
                .breadType(BreadType.BREAD)
                .description(List.of())
                .price(10_000L)
                .quantity(null)
                .build()
        );

        ProductResponse result = productFacade.update(
            memberId,
            UpdateProductRequest.builder()
                .productId(response.getId())
                .name("updated_test_product")
                .breadType(BreadType.CAKES)
                .description(List.of())
                .price(20_000L)
                .quantity(100L)
                .build()
        );

        assertThat(result).isNotNull().isInstanceOf(ProductResponse.class);

        assertThat(result.getId()).isEqualTo(result.getId());
        assertThat(result.getName()).isEqualTo("updated_test_product");
    }

    @Test
    @DisplayName("상품 판매를 종료할 수 있어요.")
    public void stopProduct() {
        ProductResponse response = productFacade.create(
            memberId,
            CreateProductRequest.builder()
                .storeId(storeId)
                .name("test_product")
                .breadType(BreadType.BREAD)
                .description(List.of())
                .price(10_000L)
                .quantity(null)
                .build()
        );

        ProductResponse result = productFacade.stop(
            memberId,
            response.getId()
        );

        assertThat(result).isNotNull().isInstanceOf(ProductResponse.class);

        assertThat(result.getId()).isEqualTo(result.getId());
        assertThat(result.isStatus()).isFalse();
    }
}