package com.github.org.todaybread.todaybread.auth.application.oauth;

import com.github.org.todaybread.todaybread.auth.domain.auth.AuthType;

public interface OAuthService {

    String getClientId(AuthType type, String token);
}
