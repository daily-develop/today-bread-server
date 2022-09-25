package com.github.org.todaybread.todaybread.auth.application.oauth;

import com.github.org.todaybread.todaybread.auth.domain.auth.AuthType;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Profile(value = "test")
@Service
public class OAuthServiceImpl implements OAuthService {

    @Override
    public String getClientId(AuthType type, String token) {
        return token.replaceAll("-", "").substring(0, 10);
    }
}
