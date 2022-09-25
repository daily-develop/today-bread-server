package com.github.org.todaybread.todaybread.auth.application.auth;

import com.github.org.todaybread.todaybread.auth.infra.http.request.ReissueRequest;
import com.github.org.todaybread.todaybread.auth.infra.http.request.SignInRequest;
import com.github.org.todaybread.todaybread.auth.infra.http.request.SignUpRequest;
import com.github.org.todaybread.todaybread.auth.infra.http.response.TokenResponse;

public interface AuthService {

    TokenResponse login(SignInRequest request);

    TokenResponse create(SignUpRequest request);

    TokenResponse reissue(ReissueRequest request);

    Boolean logout(String memberId);
}
