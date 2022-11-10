package com.github.org.todaybread.todaybread.auth.application.auth;

import com.github.org.todaybread.todaybread.auth.application.oauth.OAuthService;
import com.github.org.todaybread.todaybread.auth.application.token.TokenServiceImpl;
import com.github.org.todaybread.todaybread.auth.domain.auth.Auth;
import com.github.org.todaybread.todaybread.auth.domain.auth.AuthRepository;
import com.github.org.todaybread.todaybread.auth.exceptions.AlreadyExistingAuthException;
import com.github.org.todaybread.todaybread.auth.exceptions.InvalidTokenException;
import com.github.org.todaybread.todaybread.auth.exceptions.NotFoundAuthException;
import com.github.org.todaybread.todaybread.auth.infra.http.request.ReissueRequest;
import com.github.org.todaybread.todaybread.auth.infra.http.request.SignInRequest;
import com.github.org.todaybread.todaybread.auth.infra.http.request.SignUpRequest;
import com.github.org.todaybread.todaybread.auth.infra.http.response.TokenResponse;
import com.github.org.todaybread.todaybread.customer.application.service.CustomerServiceImpl;
import com.github.org.todaybread.todaybread.customer.domain.Customer;
import com.github.org.todaybread.todaybread.file.application.facade.FileFacade;
import com.github.org.todaybread.todaybread.file.domain.File;
import com.github.org.todaybread.todaybread.file.domain.FileType;
import com.github.org.todaybread.todaybread.member.domain.Member;
import com.github.org.todaybread.todaybread.member.domain.MemberRepository;
import com.github.org.todaybread.todaybread.steppay.customer.application.SteppayCustomerService;
import com.github.org.todaybread.todaybread.steppay.customer.infra.request.SteppayCreateCustomerRequest;
import com.github.org.todaybread.todaybread.steppay.customer.infra.request.SteppayShippingRequest;
import com.github.org.todaybread.todaybread.steppay.customer.infra.response.SteppayCustomerResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AuthServiceImpl implements AuthService {

    private final TokenServiceImpl tokenProvider;
    private final OAuthService oAuthService;
    private final FileFacade fileFacade;
    private final AuthRepository authRepository;
    private final MemberRepository memberRepository;
    private final SteppayCustomerService steppayCustomerService;
    private final CustomerServiceImpl customerService;


    @Override
    @Transactional
    public TokenResponse login(SignInRequest request) {
        Auth auth = authRepository.getByAuthTypeAndClientId(
            request.getType(),
            oAuthService.getClientId(request.getType(), request.getToken())
        ).orElseThrow(NotFoundAuthException::new);

        return tokenProvider.create(auth).toResponse();
    }

    @Override
    @Transactional
    public TokenResponse create(SignUpRequest request) {
        String clientId = oAuthService.getClientId(request.getType(), request.getToken());
        if (authRepository.getByAuthTypeAndClientId(request.getType(), clientId).isPresent()) {
            throw new AlreadyExistingAuthException();
        }

        SteppayCustomerResponse response = steppayCustomerService.createCustomer(
            SteppayCreateCustomerRequest.builder()
                .name(request.getName())
                .email(request.getEmail())
                .phone(request.getPhone())
                .shipping(
                    SteppayShippingRequest.builder()
                        .postcode(request.getPostcode())
                        .address1(request.getAddress1())
                        .address2(request.getAddress2())
                        .build()
                )
                .build()
        );

        Member member = memberRepository.save(
            Member.builder()
                .steppayId(response.getId())
                .name(request.getName())
                .email(request.getEmail())
                .phone(request.getPhone())
                .postcode(request.getPostcode())
                .address1(request.getAddress1())
                .address2(request.getAddress2())
                .build()
        );

        customerService.save(
            Customer.builder()
                .steppayId(response.getId())
                .member(member)
                .build()
        );

        Auth auth = authRepository.save(
            Auth.builder()
                .type(request.getType())
                .clientId(clientId)
                .member(member)
                .build()
        );

        if (request.getProfileImage() != null) {
            File profileImage = fileFacade.upload(
                auth.getMember().getId().toString(),
                FileType.PROFILE,
                request.getProfileImage()
            );
            auth.getMember().updateProfileImage(profileImage);
        }

        return tokenProvider.create(auth).toResponse();
    }

    @Override
    public TokenResponse reissue(ReissueRequest request) {
        if (!tokenProvider.validation(request.getRefreshToken())) {
            throw new InvalidTokenException();
        }

        Auth auth = authRepository.getByMemberId(tokenProvider.parse(request.getRefreshToken()))
            .orElseThrow(NotFoundAuthException::new);

        return tokenProvider.create(auth).toResponse();
    }

    @Override
    public Boolean logout(String memberId) {
        Auth auth = authRepository.getByMemberId(memberId)
            .orElseThrow(NotFoundAuthException::new);

        return tokenProvider.remove(auth);
    }
}
