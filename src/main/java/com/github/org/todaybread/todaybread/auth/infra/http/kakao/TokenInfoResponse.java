package com.github.org.todaybread.todaybread.auth.infra.http.kakao;

import lombok.Getter;

@Getter
public class TokenInfoResponse {

	private String id;
	private String expires_in;
	private String app_id;
}
