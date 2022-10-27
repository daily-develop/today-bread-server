package com.github.org.todaybread.todaybread.product.application.facade;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import com.github.org.todaybread.todaybread.manager.infra.http.request.CreateManagerRequest;
import com.github.org.todaybread.todaybread.member.domain.Member;
import com.github.org.todaybread.todaybread.member.domain.MemberRepository;
import com.github.org.todaybread.todaybread.product.domain.BreadType;
import com.github.org.todaybread.todaybread.product.infra.http.request.CreateProductRequest;
import com.github.org.todaybread.todaybread.product.infra.http.response.ProductResponse;
import com.github.org.todaybread.todaybread.steppay.product.application.SteppayProductService;
import com.github.org.todaybread.todaybread.steppay.product.infra.request.SteppayCreateProductRequest;
import com.github.org.todaybread.todaybread.steppay.product.infra.response.SteppayProductResponse;
import com.github.org.todaybread.todaybread.store.application.facade.StoreFacade;
import com.github.org.todaybread.todaybread.store.infra.http.request.CreateStoreRequest;
import java.time.LocalDateTime;
import java.util.UUID;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
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

    @MockBean
    private SteppayProductService steppayProductService;

    @BeforeEach
    public void beforeEach() {
        memberId = memberRepository.save(
            Member.builder()
                .name("test_name")
                .email("test@email.com")
                .phone("010-0000-0000")
                .address("경기도 성남시 분당구")
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
        when(steppayProductService.create(any(SteppayCreateProductRequest.class)))
            .thenReturn(
                SteppayProductResponse.builder()
                    .id(1000)
                    .createdAt(LocalDateTime.now())
                    .modifiedAt(LocalDateTime.now())
                    .code(UUID.randomUUID().toString().replaceAll("-", ""))
                    .type("BOX")
                    .status("SALE")
                    .name("test_product")
                    .featuredImageUrl("test_featured_image_url")
                    .description("test_description")
                    .prices(null)
                    .build()
            );

        ProductResponse response = productFacade.create(
            memberId,
            CreateProductRequest.builder()
                .storeId(storeId)
                .name("")
                .breadType(BreadType.BREAD)
                .description("")
                .price(10_000)
                .quantity(null)
                .build()
        );

        assertThat(response).isNotNull().isInstanceOf(ProductResponse.class);

        assertThat(response.getName()).isEqualTo("test_product");
        assertThat(response.getDescription()).isEqualTo("test_description");
        assertThat(response.getBreadType()).isEqualTo(BreadType.BREAD);
        assertThat(response.getPrice()).isEqualTo(10000);
        assertThat(response.getQuantity()).isEqualTo(null);
    }

    @Test
    @DisplayName("상품을 조회할 수 있어요.")
    public void findProduct() {
        when(steppayProductService.create(any(SteppayCreateProductRequest.class)))
            .thenReturn(
                SteppayProductResponse.builder()
                    .id(1000)
                    .createdAt(LocalDateTime.now())
                    .modifiedAt(LocalDateTime.now())
                    .code(UUID.randomUUID().toString().replaceAll("-", ""))
                    .type("BOX")
                    .status("SALE")
                    .name("test_product")
                    .featuredImageUrl("test_featured_image_url")
                    .description("test_description")
                    .prices(null)
                    .build()
            );

        ProductResponse response = productFacade.create(
            memberId,
            CreateProductRequest.builder()
                .storeId(storeId)
                .name("")
                .breadType(BreadType.BREAD)
                .description("")
                .price(10_000)
                .quantity(null)
                .build()
        );

        ProductResponse result = productFacade.getById(response.getId());

        assertThat(result).isNotNull().isInstanceOf(ProductResponse.class);

        assertThat(result).usingRecursiveComparison().isEqualTo(response);
    }
}