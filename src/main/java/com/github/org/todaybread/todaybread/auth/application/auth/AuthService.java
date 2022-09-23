package com.github.org.todaybread.todaybread.auth.application.auth;

import com.github.org.todaybread.todaybread.auth.domain.token.Token;
import com.github.org.todaybread.todaybread.auth.infra.http.request.ReissueRequest;
import com.github.org.todaybread.todaybread.auth.infra.http.request.SignInRequest;
import com.github.org.todaybread.todaybread.auth.infra.http.request.SignUpRequest;

public interface AuthService {

    Token signIn(SignInRequest request);

    Token signUp(SignUpRequest request);

    Token reissue(ReissueRequest request);

    boolean signOut(String memberId);
}
