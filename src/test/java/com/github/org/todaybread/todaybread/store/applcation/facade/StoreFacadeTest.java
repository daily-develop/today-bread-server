package com.github.org.todaybread.todaybread.store.applcation.facade;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import com.github.org.todaybread.todaybread.auth.application.auth.AuthServiceImpl;
import com.github.org.todaybread.todaybread.auth.application.token.TokenServiceImpl;
import com.github.org.todaybread.todaybread.auth.domain.auth.AuthType;
import com.github.org.todaybread.todaybread.auth.infra.http.request.SignUpRequest;
import com.github.org.todaybread.todaybread.auth.infra.http.response.TokenResponse;
import com.github.org.todaybread.todaybread.config.EmbeddedRedisConfig;
import com.github.org.todaybread.todaybread.manager.infra.http.request.CreateManagerRequest;
import com.github.org.todaybread.todaybread.member.domain.Member;
import com.github.org.todaybread.todaybread.member.infra.persistence.MemberRepositoryImpl;
import com.github.org.todaybread.todaybread.store.application.facade.StoreFacadeImpl;
import com.github.org.todaybread.todaybread.store.exceptions.NotFoundStoreException;
import com.github.org.todaybread.todaybread.store.infra.http.request.CreateStoreRequest;
import com.github.org.todaybread.todaybread.store.infra.http.request.UpdateStoreRequest;
import com.github.org.todaybread.todaybread.store.infra.http.response.StoreResponse;
import java.util.List;
import java.util.UUID;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.transaction.annotation.Transactional;

@Import(value = EmbeddedRedisConfig.class)
@SpringBootTest
@Transactional
public class StoreFacadeTest {

    @Autowired
    private StoreFacadeImpl storeFacade;
    @Autowired
    private AuthServiceImpl authService;
    @Autowired
    private TokenServiceImpl tokenService;
    @Autowired
    private MemberRepositoryImpl memberRepository;

    @Test
    @DisplayName("가게를 등록할 수 있어요.")
    public void create() {
        TokenResponse response = authService.create(
            SignUpRequest.builder()
                .type(AuthType.KAKAO)
                .token(UUID.randomUUID().toString())
                .name("test_name")
                .email("test@test.com")
                .phone("010-0000-0000")
                .address("서울시 강남구 삼성동")
                .build()
        );
        Member member = memberRepository.getById(tokenService.parse(response.getAccessToken()))
            .orElse(null);

        StoreResponse store = storeFacade.create(
            member.getId().toString(),
            CreateStoreRequest.builder()
                .name("배지의 쿠키하우스")
                .description("배지네 쿠키하우스입니다. 놀러오세요!")
                .location("서울시 강남구 삼성동")
                .phone("010-9999-9999")
                .manager(
                    CreateManagerRequest.builder()
                        .nickname("사장님")
                        .build()
                ).build());

        assertThat(store).isNotNull().isInstanceOf(StoreResponse.class);
        assertThat(store.getName()).isEqualTo("배지의 쿠키하우스");
        assertThat(store.getDescription()).isEqualTo("배지네 쿠키하우스입니다. 놀러오세요!");
        assertThat(store.getLocation()).isEqualTo("서울시 강남구 삼성동");
    }

    @Test
    @DisplayName("가게를 수정할 수 있어요.")
    public void update() {
        TokenResponse response = authService.create(
            SignUpRequest.builder()
                .type(AuthType.KAKAO)
                .token(UUID.randomUUID().toString())
                .name("test_name")
                .email("test@test.com")
                .phone("010-0000-0000")
                .address("서울시 강남구 삼성동")
                .build()
        );
        Member member = memberRepository.getById(tokenService.parse(response.getAccessToken()))
            .orElse(null);

        StoreResponse store = storeFacade.create(
            member.getId().toString(),
            CreateStoreRequest.builder()
                .name("배지의 쿠키하우스")
                .description("배지네 쿠키하우스입니다. 놀러오세요!")
                .location("서울시 강남구 삼성동")
                .phone("010-9999-9999")
                .manager(
                    CreateManagerRequest.builder()
                        .nickname("사장님")
                        .build()
                ).build());

        StoreResponse updatedStore = storeFacade.update(
            member.getId().toString(),
            UpdateStoreRequest.builder()
                .storeId(store.getId())
                .name("배지네 식빵하우스")
                .description("배지네 식빵하우스입니다~")
                .build()
        );

        assertThat(store).isNotNull().isInstanceOf(StoreResponse.class);
        assertThat(updatedStore).isNotNull().isInstanceOf(StoreResponse.class);
        assertThat(store.getName()).isEqualTo("배지의 쿠키하우스");
        assertThat(store.getDescription()).isEqualTo("배지네 쿠키하우스입니다. 놀러오세요!");
        assertThat(updatedStore.getName()).isEqualTo("배지네 식빵하우스");
        assertThat(updatedStore.getDescription()).isEqualTo("배지네 식빵하우스입니다~");
    }

    @Test
    @DisplayName("가게를 삭제할 수 있어요.")
    public void delete() {
        TokenResponse response = authService.create(
            SignUpRequest.builder()
                .type(AuthType.KAKAO)
                .token(UUID.randomUUID().toString())
                .name("test_name")
                .email("test@test.com")
                .phone("010-0000-0000")
                .address("서울시 강남구 삼성동")
                .build()
        );
        Member member = memberRepository.getById(tokenService.parse(response.getAccessToken()))
            .orElse(null);

        StoreResponse store = storeFacade.create(
            member.getId().toString(),
            CreateStoreRequest.builder()
                .name("배지의 쿠키하우스")
                .description("배지네 쿠키하우스입니다. 놀러오세요!")
                .location("서울시 강남구 삼성동")
                .phone("010-9999-9999")
                .manager(
                    CreateManagerRequest.builder()
                        .nickname("사장님")
                        .build()
                ).build());

        Boolean result = storeFacade.delete(member.getId().toString(), store.getId());

        assertThat(store).isNotNull().isInstanceOf(StoreResponse.class);
        assertThat(store.getName()).isEqualTo("배지의 쿠키하우스");
        assertThat(result).isTrue();

        Assertions.assertThrows(
            NotFoundStoreException.class,
            () -> storeFacade.get(store.getId())
        );
    }

    @Test
    @DisplayName("가게를 조회할 수 있어요.")
    public void get() {
        TokenResponse response = authService.create(
            SignUpRequest.builder()
                .type(AuthType.KAKAO)
                .token(UUID.randomUUID().toString())
                .name("test_name")
                .email("test@test.com")
                .phone("010-0000-0000")
                .address("서울시 강남구 삼성동")
                .build()
        );
        Member member = memberRepository.getById(tokenService.parse(response.getAccessToken()))
            .orElse(null);

        StoreResponse store = storeFacade.create(
            member.getId().toString(),
            CreateStoreRequest.builder()
                .name("배지의 쿠키하우스")
                .description("배지네 쿠키하우스입니다. 놀러오세요!")
                .location("서울시 강남구 삼성동")
                .phone("010-9999-9999")
                .manager(
                    CreateManagerRequest.builder()
                        .nickname("사장님")
                        .build()
                ).build());

        StoreResponse resultStore = storeFacade.get(store.getId());

        assertThat(store).isNotNull().isInstanceOf(StoreResponse.class);
        assertThat(resultStore).isNotNull().isInstanceOf(StoreResponse.class);
    }

    @Test
    @DisplayName("가게 리스트를 조회할 수 있어요.")
    public void getList() {
        TokenResponse response = authService.create(
            SignUpRequest.builder()
                .type(AuthType.KAKAO)
                .token(UUID.randomUUID().toString())
                .name("test_name")
                .email("test@test.com")
                .phone("010-0000-0000")
                .address("서울시 강남구 삼성동")
                .build()
        );
        Member member = memberRepository.getById(tokenService.parse(response.getAccessToken()))
            .orElse(null);

        storeFacade.create(
            member.getId().toString(),
            CreateStoreRequest.builder()
                .name("배지의 쿠키하우스")
                .description("배지네 쿠키하우스입니다. 놀러오세요!")
                .location("서울시 강남구 삼성동")
                .phone("010-9999-9999")
                .manager(
                    CreateManagerRequest.builder()
                        .nickname("사장님")
                        .build()
                ).build());

        storeFacade.create(
            member.getId().toString(),
            CreateStoreRequest.builder()
                .name("배지의 식빵하우스")
                .description("배지네 식빵하우스입니다. 놀러오세요!")
                .location("서울시 강남구 삼성동")
                .phone("010-9999-9999")
                .manager(
                    CreateManagerRequest.builder()
                        .nickname("사장님")
                        .build()
                ).build());

        storeFacade.create(
            member.getId().toString(),
            CreateStoreRequest.builder()
                .name("베어 곰달")
                .description("베어 곰달입니다. 놀러오세요!")
                .location("서울시 강남구 삼성동")
                .phone("010-9999-9999")
                .manager(
                    CreateManagerRequest.builder()
                        .nickname("사장님")
                        .build()
                ).build());

        List<StoreResponse> stores = storeFacade.getList(1, 10, "");

        assertThat(stores.size()).isEqualTo(3);
        assertThat(stores.get(0)).isNotNull().isInstanceOf(StoreResponse.class);
    }


    @Test
    @DisplayName("search에 null을 넘겨줘도 가게 리스트를 조회할 수 있어요.")
    public void getListContainNull() {
        TokenResponse response = authService.create(
            SignUpRequest.builder()
                .type(AuthType.KAKAO)
                .token(UUID.randomUUID().toString())
                .name("test_name")
                .email("test@test.com")
                .phone("010-0000-0000")
                .address("서울시 강남구 삼성동")
                .build()
        );
        Member member = memberRepository.getById(tokenService.parse(response.getAccessToken()))
            .orElse(null);

        storeFacade.create(
            member.getId().toString(),
            CreateStoreRequest.builder()
                .name("배지의 쿠키하우스")
                .description("배지네 쿠키하우스입니다. 놀러오세요!")
                .location("서울시 강남구 삼성동")
                .phone("010-9999-9999")
                .manager(
                    CreateManagerRequest.builder()
                        .nickname("사장님")
                        .build()
                ).build());

        storeFacade.create(
            member.getId().toString(),
            CreateStoreRequest.builder()
                .name("배지의 식빵하우스")
                .description("배지네 식빵하우스입니다. 놀러오세요!")
                .location("서울시 강남구 삼성동")
                .phone("010-9999-9999")
                .manager(
                    CreateManagerRequest.builder()
                        .nickname("사장님")
                        .build()
                ).build());

        storeFacade.create(
            member.getId().toString(),
            CreateStoreRequest.builder()
                .name("베어 곰달")
                .description("베어 곰달입니다. 놀러오세요!")
                .location("서울시 강남구 삼성동")
                .phone("010-9999-9999")
                .manager(
                    CreateManagerRequest.builder()
                        .nickname("사장님")
                        .build()
                ).build());

        List<StoreResponse> stores = storeFacade.getList(1, 10, null);

        assertThat(stores.size()).isEqualTo(3);
        assertThat(stores.get(0)).isNotNull().isInstanceOf(StoreResponse.class);
    }

    @Test
    @DisplayName("가게 리스트를 검색할 수 있어요.")
    public void getListContainSearch() {
        TokenResponse response = authService.create(
            SignUpRequest.builder()
                .type(AuthType.KAKAO)
                .token(UUID.randomUUID().toString())
                .name("test_name")
                .email("test@test.com")
                .phone("010-0000-0000")
                .address("서울시 강남구 삼성동")
                .build()
        );
        Member member = memberRepository.getById(tokenService.parse(response.getAccessToken()))
            .orElse(null);

        storeFacade.create(
            member.getId().toString(),
            CreateStoreRequest.builder()
                .name("배지의 쿠키하우스")
                .description("배지네 쿠키하우스입니다. 놀러오세요!")
                .location("서울시 강남구 삼성동")
                .phone("010-9999-9999")
                .manager(
                    CreateManagerRequest.builder()
                        .nickname("사장님")
                        .build()
                ).build()
        );

        storeFacade.create(
            member.getId().toString(),
            CreateStoreRequest.builder()
                .name("배지의 식빵하우스")
                .description("배지네 식빵하우스입니다. 놀러오세요!")
                .location("서울시 강남구 삼성동")
                .phone("010-9999-9999")
                .manager(
                    CreateManagerRequest.builder()
                        .nickname("사장님")
                        .build()
                ).build());

        storeFacade.create(
            member.getId().toString(),
            CreateStoreRequest.builder()
                .name("베어 곰달")
                .description("베어 곰달입니다. 놀러오세요!")
                .location("서울시 강남구 삼성동")
                .phone("010-9999-9999")
                .manager(
                    CreateManagerRequest.builder()
                        .nickname("사장님")
                        .build()
                ).build());

        List<StoreResponse> stores = storeFacade.getList(1, 10, "배지의");

        assertThat(stores.size()).isEqualTo(2);
        assertThat(stores.get(0)).isNotNull().isInstanceOf(StoreResponse.class);
    }

    @Test
    @DisplayName("관리하고 있는 가게 목록을 조회할 수 있어요.")
    public void getManagedStore() {
        TokenResponse response = authService.create(
            SignUpRequest.builder()
                .type(AuthType.KAKAO)
                .token(UUID.randomUUID().toString())
                .name("test_nickname")
                .email("test@test.com")
                .phone("010-0000-0000")
                .address("서울시 강남구 삼성동")
                .build()
        );
        Member member = memberRepository.getById(tokenService.parse(response.getAccessToken()))
            .orElse(null);

        storeFacade.create(
            member.getId().toString(),
            CreateStoreRequest.builder()
                .name("배지의 쿠키하우스")
                .description("배지네 쿠키하우스입니다. 놀러오세요!")
                .location("서울시 강남구 삼성동")
                .phone("010-0000-0000")
                .manager(
                    CreateManagerRequest.builder()
                        .nickname("사장님")
                        .build()
                ).build()
        );

        List<StoreResponse> stores = storeFacade.getByMemberId(member.getId().toString());

        assertThat(stores).isNotNull();
        assertThat(stores.size()).isEqualTo(1);
    }
}
