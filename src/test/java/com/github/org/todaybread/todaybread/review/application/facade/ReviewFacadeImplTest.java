package com.github.org.todaybread.todaybread.review.application.facade;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import com.github.org.todaybread.todaybread.manager.infra.http.request.CreateManagerRequest;
import com.github.org.todaybread.todaybread.member.domain.Member;
import com.github.org.todaybread.todaybread.member.infra.persistence.MemberRepositoryImpl;
import com.github.org.todaybread.todaybread.product.application.facade.ProductFacadeImpl;
import com.github.org.todaybread.todaybread.product.domain.BreadType;
import com.github.org.todaybread.todaybread.product.infra.http.request.CreateProductRequest;
import com.github.org.todaybread.todaybread.review.infra.http.request.CreateReviewRequest;
import com.github.org.todaybread.todaybread.review.infra.http.response.ReviewListResponse;
import com.github.org.todaybread.todaybread.review.infra.http.response.ReviewResponse;
import com.github.org.todaybread.todaybread.steppay.product.application.SteppayProductService;
import com.github.org.todaybread.todaybread.steppay.product.infra.request.SteppayCreateProductRequest;
import com.github.org.todaybread.todaybread.steppay.product.infra.response.SteppayProductResponse;
import com.github.org.todaybread.todaybread.store.application.facade.StoreFacadeImpl;
import com.github.org.todaybread.todaybread.store.infra.http.request.CreateStoreRequest;
import java.time.LocalDateTime;
import java.util.List;
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
class ReviewFacadeImplTest {

    private String memberId;
    private String productId;

    @Autowired
    private ReviewFacadeImpl reviewFacade;
    @Autowired
    private ProductFacadeImpl productFacade;
    @MockBean
    private SteppayProductService steppayProductService;
    @Autowired
    private StoreFacadeImpl storeFacade;
    @Autowired
    private MemberRepositoryImpl memberRepository;

    @BeforeEach
    public void beforeEach() {
        memberId = memberRepository.save(
            Member.builder()
                .name("test_name")
                .email("test@email.com")
                .phone("010-0000-0000")
                .postcode("12345")
                .address1("서울시")
                .address2("특별시 ")
                .build()
        ).getId().toString();

        String storeId = storeFacade.create(
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
                    .prices(null)
                    .build()
            );

        productId = productFacade.create(
            memberId,
            CreateProductRequest.builder()
                .storeId(storeId)
                .name("test_product")
                .breadType(BreadType.BREAD)
                .description(List.of())
                .price(10_000)
                .quantity(null)
                .build()
        ).getId();
    }

    @Test
    @DisplayName("리뷰를 등록할 수 있어요.")
    public void create() {
        ReviewResponse review = reviewFacade.create(memberId, CreateReviewRequest.builder()
            .productId(productId)
            .content("test_content")
            .score(4.5F)
            .build());

        assertThat(review).isNotNull().isInstanceOf(ReviewResponse.class);

        assertThat(review.getScore()).isEqualTo(4.5F);
        assertThat(review.getContent()).isEqualTo("test_content");
    }

    @Test
    @DisplayName("리뷰를 조회할 수 있어요.")
    public void getList() {
        reviewFacade.create(memberId, CreateReviewRequest.builder()
            .productId(productId)
            .content("test_content")
            .score(4.5F)
            .build());
        reviewFacade.create(memberId, CreateReviewRequest.builder()
            .productId(productId)
            .content("test_content")
            .score(4.5F)
            .build());
        reviewFacade.create(memberId, CreateReviewRequest.builder()
            .productId(productId)
            .content("test_content")
            .score(4.5F)
            .build());
        reviewFacade.create(memberId, CreateReviewRequest.builder()
            .productId(productId)
            .content("test_content")
            .score(4.5F)
            .build());
        reviewFacade.create(memberId, CreateReviewRequest.builder()
            .productId(productId)
            .content("test_content")
            .score(4.5F)
            .build());
        reviewFacade.create(memberId, CreateReviewRequest.builder()
            .productId(productId)
            .content("test_content")
            .score(4.5F)
            .build());
        reviewFacade.create(memberId, CreateReviewRequest.builder()
            .productId(productId)
            .content("test_content")
            .score(4.5F)
            .build());
        reviewFacade.create(memberId, CreateReviewRequest.builder()
            .productId(productId)
            .content("test_content")
            .score(4.5F)
            .build());
        reviewFacade.create(memberId, CreateReviewRequest.builder()
            .productId(productId)
            .content("test_content")
            .score(4.5F)
            .build());
        reviewFacade.create(memberId, CreateReviewRequest.builder()
            .productId(productId)
            .content("test_content")
            .score(4.5F)
            .build());

        ReviewListResponse responses = reviewFacade.getList(productId, 1, 5);
        assertThat(responses.getContent().size()).isEqualTo(5);
    }

    @Test
    @DisplayName("리뷰를 삭제할 수 있어요.")
    public void delete() {
        ReviewResponse review = reviewFacade.create(memberId, CreateReviewRequest.builder()
            .productId(productId)
            .content("test_content")
            .score(4.5F)
            .build());

        Boolean response = reviewFacade.delete(memberId, review.getId());
        assertThat(response).isTrue();
    }
}