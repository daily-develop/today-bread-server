package com.github.org.todaybread.todaybread.auth.application.oauth;

import com.github.org.todaybread.todaybread.auth.domain.auth.AuthType;
import com.github.org.todaybread.todaybread.auth.exceptions.InvalidOAuthException;
import com.github.org.todaybread.todaybread.auth.infra.http.oauth.KakaoTokenResponse;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class OAuthServiceImpl implements OAuthService {

    RestTemplate template = new RestTemplate();

    @Override
    public String getClientId(AuthType type, String token) {
        if (type.equals(AuthType.KAKAO)) {
            HttpHeaders headers = new HttpHeaders();
            headers.set("Authorization", "Bearer " + token);

            ResponseEntity<KakaoTokenResponse> response = template.exchange(
                "https://kapi.kakao.com/v1/user/access_token_info",
                HttpMethod.GET,
                new HttpEntity<>(null, headers),
                KakaoTokenResponse.class
            );

            if (response.getBody() == null) {
                throw new InvalidOAuthException();
            }
            return response.getBody().getId().toString();
        }

        return null;
    }
}
