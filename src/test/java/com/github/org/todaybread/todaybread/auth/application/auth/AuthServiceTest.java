package com.github.org.todaybread.todaybread.auth.application.auth;

import static org.assertj.core.api.Assertions.assertThat;

import com.github.org.todaybread.todaybread.auth.application.token.TokenServiceImpl;
import com.github.org.todaybread.todaybread.auth.domain.auth.AuthType;
import com.github.org.todaybread.todaybread.auth.exceptions.AlreadyExistingAuthException;
import com.github.org.todaybread.todaybread.auth.exceptions.NotFoundAuthException;
import com.github.org.todaybread.todaybread.auth.infra.http.request.ReissueRequest;
import com.github.org.todaybread.todaybread.auth.infra.http.request.SignInRequest;
import com.github.org.todaybread.todaybread.auth.infra.http.request.SignUpRequest;
import com.github.org.todaybread.todaybread.auth.infra.http.response.TokenResponse;
import com.github.org.todaybread.todaybread.config.EmbeddedRedisConfig;
import com.github.org.todaybread.todaybread.member.domain.Member;
import com.github.org.todaybread.todaybread.member.infra.persistence.MemberRepositoryImpl;
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
class AuthServiceTest {

    @Autowired
    private AuthServiceImpl authService;
    @Autowired
    private TokenServiceImpl tokenService;
    @Autowired
    private MemberRepositoryImpl memberRepository;

    @Test
    @DisplayName("회원가입을 할 수 있어요.")
    void signUp() {
        TokenResponse response = authService.create(
            SignUpRequest.builder()
                .type(AuthType.KAKAO)
                .token(UUID.randomUUID().toString())
                .nickname("test_nickname")
                .email("test@test.com")
                .phone("010-0000-0000")
                .address("서울시 강남구 삼성동")
                .build()
        );

        assertThat(response).isNotNull().isInstanceOf(TokenResponse.class);

        Member member = memberRepository.getById(tokenService.parse(response.getAccessToken()))
            .orElse(null);

        assertThat(member).isNotNull().isInstanceOf(Member.class);
        assertThat(member.getNickname()).isInstanceOf(String.class).isEqualTo("test_nickname");
        assertThat(member.getEmail()).isInstanceOf(String.class).isEqualTo("test@test.com");
        assertThat(member.getPhone()).isInstanceOf(String.class).isEqualTo("010-0000-0000");
        assertThat(member.getAddress()).isInstanceOf(String.class).isEqualTo("서울시 강남구 삼성동");
    }

    @Test
    @DisplayName("회원가입에 실패해요")
    void signUpFailed() {
        String token = UUID.randomUUID().toString();

        authService.create(
            SignUpRequest.builder()
                .type(AuthType.KAKAO)
                .token(token)
                .nickname("test_nickname")
                .email("test@test.com")
                .phone("010-0000-0000")
                .address("서울시 강남구 삼성동")
                .build()
        );

        Assertions.assertThrows(
            AlreadyExistingAuthException.class,
            () -> authService.create(
                SignUpRequest.builder()
                    .type(AuthType.KAKAO)
                    .token(token)
                    .nickname("test_nickname")
                    .email("test@test.com")
                    .phone("010-0000-0000")
                    .address("서울시 강남구 삼성동")
                    .build()
            )
        );

        Assertions.assertThrows(
            AlreadyExistingAuthException.class,
            () -> authService.create(
                SignUpRequest.builder()
                    .type(AuthType.KAKAO)
                    .token(token)
                    .nickname("other_test_nickname")
                    .email("other_test@test.com")
                    .phone("010-1111-1111")
                    .address("서울시 강남구 청담동")
                    .build()
            )
        );
    }

    @Test
    @DisplayName("로그인을 할 수 있어요")
    void signIn() {
        String token = UUID.randomUUID().toString();

        authService.create(
            SignUpRequest.builder()
                .type(AuthType.KAKAO)
                .token(token)
                .nickname("test_nickname")
                .email("test@test.com")
                .phone("010-0000-0000")
                .address("서울시 강남구 삼성동")
                .build()
        );

        TokenResponse response = authService.login(
            SignInRequest.builder()
                .type(AuthType.KAKAO)
                .token(token)
                .build()
        );

        assertThat(response).isNotNull().isInstanceOf(TokenResponse.class);
    }

    @Test
    @DisplayName("로그인에 실패해요")
    void signInFailed() {
        Assertions.assertThrows(
            NotFoundAuthException.class,
            () -> authService.login(
                SignInRequest.builder()
                    .type(AuthType.KAKAO)
                    .token(UUID.randomUUID().toString())
                    .build()
            )
        );
    }

    @Test
    @DisplayName("토큰을 재발급 받을 수 있어요")
    void reissue() throws InterruptedException {
        TokenResponse token = authService.create(
            SignUpRequest.builder()
                .type(AuthType.KAKAO)
                .token(UUID.randomUUID().toString())
                .nickname("test_nickname")
                .email("test@test.com")
                .phone("010-0000-0000")
                .address("서울시 강남구 삼성동")
                .build()
        );

        Thread.sleep(1000);

        TokenResponse result = authService.reissue(
            ReissueRequest.builder()
                .accessToken(token.getAccessToken())
                .refreshToken(token.getRefreshToken())
                .build()
        );

        assertThat(result).isNotNull().isInstanceOf(TokenResponse.class);
        assertThat(result.getAccessToken()).isNotEmpty().isNotEqualTo(token.getAccessToken());
        assertThat(result.getRefreshToken()).isNotEmpty().isNotEqualTo(token.getRefreshToken());
    }
}