package com.github.org.todaybread.todaybread.auth.application.token;

import com.github.org.todaybread.todaybread.auth.domain.auth.Auth;
import com.github.org.todaybread.todaybread.auth.domain.token.Token;
import org.springframework.security.core.Authentication;

public interface TokenService {

    Token create(Auth auth);

    Boolean remove(Auth auth);

    Boolean validation(String token);

    String parse(String token);

    Authentication getAuthentication(String token);
}
