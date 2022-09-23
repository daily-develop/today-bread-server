package com.github.org.todaybread.todaybread.auth.application.token;

import com.github.org.todaybread.todaybread.auth.domain.auth.Auth;
import com.github.org.todaybread.todaybread.auth.domain.token.Token;
import org.springframework.security.core.Authentication;

public interface TokenProvider {

    Token create(Auth auth);

    boolean remove(Auth auth);

    boolean validation(String token);

    Auth parse(String token);

    Authentication getAuthentication(String accessToken);
}
