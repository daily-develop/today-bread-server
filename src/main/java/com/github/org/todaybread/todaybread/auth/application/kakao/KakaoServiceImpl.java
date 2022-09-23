package com.github.org.todaybread.todaybread.auth.application.kakao;

import com.github.org.todaybread.todaybread.auth.exceptions.InvalidKakaoTokenException;
import com.github.org.todaybread.todaybread.auth.infra.http.kakao.TokenInfoResponse;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class KakaoServiceImpl implements KakaoService {

    private final String KAKAO_URL = "https://kapi.kakao.com";

    @Override
    public String getClientId(String token) {
        RestTemplate template = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + token);

        ResponseEntity<TokenInfoResponse> response = template.exchange(
            KAKAO_URL + "/v1/user/access_token_info",
            HttpMethod.GET,
            new HttpEntity<>(null, headers),
            TokenInfoResponse.class
        );

        if (response.getBody() == null) {
            throw new InvalidKakaoTokenException();
        }
        return response.getBody().getId();
    }
}
