package com.github.org.todaybread.todaybread.auth.application.auth;

import com.github.org.todaybread.todaybread.auth.application.kakao.KakaoServiceImpl;
import com.github.org.todaybread.todaybread.auth.application.token.TokenProviderImpl;
import com.github.org.todaybread.todaybread.auth.domain.auth.Auth;
import com.github.org.todaybread.todaybread.auth.domain.auth.AuthType;
import com.github.org.todaybread.todaybread.auth.domain.token.Token;
import com.github.org.todaybread.todaybread.auth.exceptions.ExistingAuthException;
import com.github.org.todaybread.todaybread.auth.exceptions.InvalidClientException;
import com.github.org.todaybread.todaybread.auth.exceptions.InvalidTokenException;
import com.github.org.todaybread.todaybread.auth.exceptions.NotFoundAuthException;
import com.github.org.todaybread.todaybread.auth.infra.http.request.ReissueRequest;
import com.github.org.todaybread.todaybread.auth.infra.http.request.SignInRequest;
import com.github.org.todaybread.todaybread.auth.infra.http.request.SignUpRequest;
import com.github.org.todaybread.todaybread.auth.infra.persistence.AuthRepositoryImpl;
import com.github.org.todaybread.todaybread.file.application.file.FileServiceImpl;
import com.github.org.todaybread.todaybread.file.domain.File;
import com.github.org.todaybread.todaybread.file.domain.FileType;
import com.github.org.todaybread.todaybread.member.domain.Member;
import com.github.org.todaybread.todaybread.member.infra.persistence.MemberRepositoryImpl;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AuthServiceImpl implements AuthService {

    private final TokenProviderImpl tokenProvider;
    private final AuthRepositoryImpl authRepository;
    private final MemberRepositoryImpl memberRepository;
    private final KakaoServiceImpl kakaoService;
    private final FileServiceImpl fileService;

    @Override
    @Transactional
    public Token signIn(SignInRequest request) {
        Auth auth = authRepository.getByAuthTypeAndClientId(
                request.getType(),
                getClientId(request.getType(), request.getToken())
            )
            .orElseThrow(NotFoundAuthException::new);

        return tokenProvider.create(auth);
    }

    @Override
    @Transactional
    public Token signUp(SignUpRequest request) {
        String clientId = getClientId(request.getType(), request.getToken());
        Auth existingAuth = authRepository.getByAuthTypeAndClientId(request.getType(), clientId)
            .orElse(null);
        if (existingAuth != null) {
            throw new ExistingAuthException();
        }

        Auth auth = authRepository.save(
            Auth.builder()
                .type(request.getType())
                .clientId(getClientId(request.getType(), request.getToken()))
                .member(
                    memberRepository.save(
                        Member.builder()
                            .nickname(request.getNickname())
                            .email(request.getEmail())
                            .build()
                    )
                ).build()
        );

        if (request.getProfileImage() != null) {
            File profileImage = fileService.upload(
                auth.getMember().getId().toString(),
                FileType.profile,
                request.getProfileImage()
            );
            auth.getMember().update(null, null, null, null, profileImage);
        }

        return tokenProvider.create(auth);
    }

    @Override
    public Token reissue(ReissueRequest request) {
        if (!tokenProvider.validation(request.getRefreshToken())) {
            throw new InvalidTokenException();
        }

        Auth auth = tokenProvider.parse(request.getRefreshToken());
        return tokenProvider.create(auth);
    }

    @Override
    public boolean signOut(String memberId) {
        Auth auth = authRepository.getByMemberId(UUID.fromString(memberId))
            .orElseThrow(NotFoundAuthException::new);

        return tokenProvider.remove(auth);
    }

    private String getClientId(AuthType type, String token) {
        if (type == AuthType.KAKAO) {
            return kakaoService.getClientId(token);
        } else {
            throw new InvalidClientException();
        }
    }
}
