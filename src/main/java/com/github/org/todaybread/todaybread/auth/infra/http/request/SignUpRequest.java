package com.github.org.todaybread.todaybread.auth.infra.http.request;

import com.github.org.todaybread.todaybread.auth.domain.auth.AuthType;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SignUpRequest {

    @NotNull
    @NotEmpty
    private AuthType type;

    @NotNull
    @NotEmpty
    private String token;

    @NotNull
    @NotEmpty
    private String nickname;

    @NotNull
    @NotEmpty
    private String email;

    private MultipartFile profileImage;

    @Builder
    public SignUpRequest(
        AuthType type,
        String token,
        String nickname,
        String email,
        MultipartFile profileImage
    ) {
        this.type = type;
        this.token = token;
        this.nickname = nickname;
        this.email = email;
        this.profileImage = profileImage;
    }
}
