package com.github.org.todaybread.todaybread.auth.infra.http.oauth;

import lombok.Getter;

@Getter
public class KakaoTokenResponse {

    private Long id;
    private Long expires_in;
    private Long app_id;
}
