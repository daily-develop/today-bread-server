package com.github.org.todaybread.todaybread.auth.infra.http.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class TokenResponse {

    String accessToken;
    String refreshToken;
}
