package com.github.org.todaybread.todaybread.review.application.facade;

import static org.assertj.core.api.Assertions.assertThat;

import com.github.org.todaybread.todaybread.customer.application.service.CustomerServiceImpl;
import com.github.org.todaybread.todaybread.customer.domain.Customer;
import com.github.org.todaybread.todaybread.manager.application.ManagerServiceImpl;
import com.github.org.todaybread.todaybread.manager.domain.Manager;
import com.github.org.todaybread.todaybread.member.domain.Member;
import com.github.org.todaybread.todaybread.member.infra.persistence.MemberRepositoryImpl;
import com.github.org.todaybread.todaybread.order.application.service.OrderServiceImpl;
import com.github.org.todaybread.todaybread.order.domain.Order;
import com.github.org.todaybread.todaybread.product.application.service.ProductServiceImpl;
import com.github.org.todaybread.todaybread.product.domain.BreadType;
import com.github.org.todaybread.todaybread.product.domain.Product;
import com.github.org.todaybread.todaybread.review.infra.http.request.CreateReviewRequest;
import com.github.org.todaybread.todaybread.review.infra.http.response.ReviewListResponse;
import com.github.org.todaybread.todaybread.review.infra.http.response.ReviewResponse;
import com.github.org.todaybread.todaybread.steppay.customer.application.SteppayCustomerService;
import com.github.org.todaybread.todaybread.steppay.customer.infra.request.SteppayCreateCustomerRequest;
import com.github.org.todaybread.todaybread.steppay.customer.infra.request.SteppayShippingRequest;
import com.github.org.todaybread.todaybread.steppay.customer.infra.response.SteppayCustomerResponse;
import com.github.org.todaybread.todaybread.store.application.service.StoreServiceImpl;
import com.github.org.todaybread.todaybread.store.domain.Store;
import java.util.List;
import java.util.UUID;
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
class ReviewFacadeImplTest {

    private String ordererId;
    private String productId;

    @Autowired
    private ReviewFacadeImpl reviewFacade;
    @Autowired
    private MemberRepositoryImpl memberRepository;
    @Autowired
    private CustomerServiceImpl customerService;
    @Autowired
    private ManagerServiceImpl managerService;
    @Autowired
    private StoreServiceImpl storeService;
    @Autowired
    private ProductServiceImpl productService;
    @Autowired
    private OrderServiceImpl orderService;
    @Autowired
    private SteppayCustomerService steppayCustomerService;


    @BeforeEach
    public void beforeEach() {
        Member manager = memberRepository.save(
            Member.builder()
                .steppayId(1000L)
                .name("test_name")
                .email("test@email.com")
                .phone("010-0000-0000")
                .postcode("12345")
                .address1("서울시")
                .address2("특별시 ")
                .build()
        );
        Store store = storeService.save(
            Store.builder()
                .name("test_store")
                .description("test_description")
                .location("test_location")
                .phone("010-1111-1111")
                .manager(
                    managerService.save(
                        Manager.builder()
                            .member(manager)
                            .nickname("manager_nickname")
                            .build()
                    )
                )
                .build()
        );

        Product product = productService.save(
            Product.builder()
                .steppayPlanCode(UUID.randomUUID().toString())
                .steppayCode(UUID.randomUUID().toString())
                .steppayId((long) (Math.random() * 10_000))
                .name("test_product")
                .breadType(BreadType.BREAD)
                .description(List.of())
                .price(10_000L)
                .quantity(null)
                .store(store)
                .build()
        );
        productId = product.getId().toString();

        Member orderer = memberRepository.save(
            Member.builder()
                .steppayId(2000L)
                .name("orderer")
                .email("test@email.com")
                .phone("010-0000-0000")
                .postcode("12345")
                .address1("서울시")
                .address2("특별시 ")
                .build()
        );
        ordererId = orderer.getId().toString();

        SteppayCustomerResponse ordererCustomerResponse = steppayCustomerService.createCustomer(
            SteppayCreateCustomerRequest.builder()
                .name("이름")
                .email("email@test.com")
                .phone("010-0000-0000")
                .shipping(
                    SteppayShippingRequest.builder()
                        .postcode("12345")
                        .address1("경기도 성남시 분당구 판교역로")
                        .address2("OOOO동 OOO호")
                        .build()
                )
                .build()
        );
        customerService.save(
            Customer.builder()
                .member(orderer)
                .steppayId(ordererCustomerResponse.getId())
                .build()
        );

        orderService.save(
            Order.builder()
                .steepayOrderCode(UUID.randomUUID().toString())
                .steppayId((long) (Math.random() * 10_000))
                .paidAmount(product.getPrice())
                .product(product)
                .member(orderer)
                .build()
        );
    }

    @Test
    @DisplayName("리뷰를 등록할 수 있어요.")
    public void create() {
        ReviewResponse review = reviewFacade.create(ordererId, CreateReviewRequest.builder()
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
        reviewFacade.create(ordererId, CreateReviewRequest.builder()
            .productId(productId)
            .content("test_content")
            .score(4.5F)
            .build());
        reviewFacade.create(ordererId, CreateReviewRequest.builder()
            .productId(productId)
            .content("test_content")
            .score(4.5F)
            .build());
        reviewFacade.create(ordererId, CreateReviewRequest.builder()
            .productId(productId)
            .content("test_content")
            .score(4.5F)
            .build());
        reviewFacade.create(ordererId, CreateReviewRequest.builder()
            .productId(productId)
            .content("test_content")
            .score(4.5F)
            .build());
        reviewFacade.create(ordererId, CreateReviewRequest.builder()
            .productId(productId)
            .content("test_content")
            .score(4.5F)
            .build());
        reviewFacade.create(ordererId, CreateReviewRequest.builder()
            .productId(productId)
            .content("test_content")
            .score(4.5F)
            .build());
        reviewFacade.create(ordererId, CreateReviewRequest.builder()
            .productId(productId)
            .content("test_content")
            .score(4.5F)
            .build());
        reviewFacade.create(ordererId, CreateReviewRequest.builder()
            .productId(productId)
            .content("test_content")
            .score(4.5F)
            .build());
        reviewFacade.create(ordererId, CreateReviewRequest.builder()
            .productId(productId)
            .content("test_content")
            .score(4.5F)
            .build());
        reviewFacade.create(ordererId, CreateReviewRequest.builder()
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
        ReviewResponse review = reviewFacade.create(ordererId, CreateReviewRequest.builder()
            .productId(productId)
            .content("test_content")
            .score(4.5F)
            .build());

        Boolean response = reviewFacade.delete(ordererId, review.getId());
        assertThat(response).isTrue();
    }

    @Test
    @DisplayName("리뷰를 생성하면 패키지 스코어를 업데이트 할 수 있어요.")
    public void createReviewWithScore() {
        reviewFacade.create(ordererId, CreateReviewRequest.builder()
            .productId(productId)
            .content("test_content")
            .score(2.0F)
            .build());

        Float avgExpect2 = productService.getById(productId).getScore();
        assertThat(avgExpect2).isEqualTo(2.0F);

        reviewFacade.create(ordererId, CreateReviewRequest.builder()
            .productId(productId)
            .content("test_content")
            .score(4.0F)
            .build());

        Float avgExpect3 = productService.getById(productId).getScore();
        assertThat(avgExpect3).isEqualTo(3.0F);
    }

    @Test
    @DisplayName("리뷰를 삭제하면 패키지 스코어를 업데이트 할 수 있어요.")
    public void deleteReviewWithScore() {
        reviewFacade.create(ordererId, CreateReviewRequest.builder()
            .productId(productId)
            .content("test_content")
            .score(1.0F)
            .build());

        Float avgExpect1 = productService.getById(productId).getScore();
        assertThat(avgExpect1).isEqualTo(1.0F);

        reviewFacade.create(ordererId, CreateReviewRequest.builder()
            .productId(productId)
            .content("test_content")
            .score(3.0F)
            .build());

        Float avgExpect2 = productService.getById(productId).getScore();
        assertThat(avgExpect2).isEqualTo(2.0F);

        ReviewResponse review = reviewFacade.create(ordererId, CreateReviewRequest.builder()
            .productId(productId)
            .content("test_content")
            .score(5.0F)
            .build());

        Float avgExpect3 = productService.getById(productId).getScore();
        assertThat(avgExpect3).isEqualTo(3.0F);

        reviewFacade.delete(ordererId, review.getId());

        Float deletedReview = productService.getById(productId).getScore();
        assertThat(deletedReview).isEqualTo(2.0F);
    }
}