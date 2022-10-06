package com.github.org.todaybread.todaybread.auth.infra.http;

import com.github.org.todaybread.todaybread.auth.application.auth.AuthServiceImpl;
import com.github.org.todaybread.todaybread.auth.infra.http.request.ReissueRequest;
import com.github.org.todaybread.todaybread.auth.infra.http.request.SignInRequest;
import com.github.org.todaybread.todaybread.auth.infra.http.request.SignUpRequest;
import com.github.org.todaybread.todaybread.auth.infra.http.response.TokenResponse;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class AuthController {

    private final AuthServiceImpl authService;

    @MutationMapping
    TokenResponse signUp(@Valid @Argument SignUpRequest request) {
        return authService.create(request);
    }

    @MutationMapping
    TokenResponse signIn(@Valid @Argument SignInRequest request) {
        return authService.login(request);
    }

    @MutationMapping
    TokenResponse reissue(@Valid @Argument ReissueRequest request) {
        return authService.reissue(request);
    }

    @MutationMapping
    @PreAuthorize(value = "hasAuthority('GENERAL')")
    Boolean logout(Authentication authentication) {
        return authService.logout(authentication.getName());
    }
}
