package com.github.org.todaybread.todaybread.auth.infra.http.request;

import com.github.org.todaybread.todaybread.auth.domain.auth.AuthType;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SignInRequest {

    @NotNull
    @NotEmpty
    private AuthType type;

    @NotNull
    @NotEmpty
    private String token;

    @Builder
    public SignInRequest(AuthType type, String token) {
        this.type = type;
        this.token = token;
    }
}
