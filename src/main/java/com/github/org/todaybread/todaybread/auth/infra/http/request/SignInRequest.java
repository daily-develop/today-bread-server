package com.github.org.todaybread.todaybread.auth.infra.http.request;

import com.github.org.todaybread.todaybread.auth.domain.auth.AuthType;
import javax.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class SignInRequest {

    @NotNull
    private AuthType type;

    @NotNull
    private String token;
}
