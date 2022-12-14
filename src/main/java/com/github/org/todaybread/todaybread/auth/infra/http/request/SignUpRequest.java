package com.github.org.todaybread.todaybread.auth.infra.http.request;

import com.github.org.todaybread.todaybread.auth.domain.auth.AuthType;
import javax.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Builder
public class SignUpRequest {

    @NotNull
    String name;

    @NotNull
    AuthType type;

    @NotNull
    String token;

    @NotNull
    String email;

    @NotNull
    String phone;

    @NotNull
    String postcode;

    @NotNull
    String address1;

    @NotNull
    String address2;

    MultipartFile profileImage;
}
