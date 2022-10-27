package com.github.org.todaybread.todaybread.auth.application.oauth;

import com.github.org.todaybread.todaybread.auth.domain.auth.AuthType;

public class OAuthServiceMock implements OAuthService {

    @Override
    public String getClientId(AuthType type, String token) {
        return token.replaceAll("-", "").substring(0, 10);
    }
}
