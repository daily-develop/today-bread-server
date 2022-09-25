package com.github.org.todaybread.todaybread.auth.infra.http.request;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ReissueRequest {

    @NotNull
    @NotEmpty
    private String accessToken;

    @NotNull
    @NotEmpty
    private String refreshToken;
}
