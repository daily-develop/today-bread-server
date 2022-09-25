package com.github.org.todaybread.todaybread.auth.domain.token;

import com.github.org.todaybread.todaybread.auth.infra.http.response.TokenResponse;
import java.io.Serializable;
import javax.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class Token implements Serializable {

    @NotBlank
    private String accessToken;

    @NotBlank
    private String refreshToken;

    public TokenResponse toResponse() {
        return TokenResponse.builder()
            .accessToken(accessToken)
            .refreshToken(refreshToken)
            .build();
    }
}
