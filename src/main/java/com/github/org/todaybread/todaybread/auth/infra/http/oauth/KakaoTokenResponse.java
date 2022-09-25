package com.github.org.todaybread.todaybread.auth.infra.http.oauth;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class KakaoTokenResponse {

    String id;
    String expires_in;
    String app_id;
}
