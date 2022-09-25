package com.github.org.todaybread.todaybread.member.domain;

import com.github.org.todaybread.todaybread.common.domain.Core;
import com.github.org.todaybread.todaybread.file.domain.File;
import com.github.org.todaybread.todaybread.member.infra.http.response.MemberResponse;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;
import org.springframework.util.Assert;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member extends Core {

    @NotBlank
    private String nickname;

    @NotBlank
    private String phone;

    @NotBlank
    @Pattern(regexp = "^[a-zA-Z0-9+-_.]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+$")
    private String email;

    @NotBlank
    private String address;

    @OneToOne(targetEntity = File.class, fetch = FetchType.LAZY, cascade = CascadeType.REMOVE, orphanRemoval = true)
    @JoinColumn(name = "file_id")
    private File profileImage;

    @Builder
    public Member(
        String nickname,
        String phone,
        String email,
        String address,
        File profileImage
    ) {
        Assert.hasText(nickname, "nickname must not be empty");
        Assert.hasText(phone, "phone must not be empty");
        Assert.hasText(email, "email must not be empty");
        Assert.hasText(address, "address must not be empty");

        this.nickname = nickname;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.profileImage = profileImage;
    }

    public void update(
        @Nullable String nickname,
        @Nullable String phone,
        @Nullable String email,
        @Nullable String address,
        @Nullable File profileImage
    ) {
        if (nickname != null) {
            this.nickname = nickname;
        }
        if (phone != null) {
            this.phone = phone;
        }
        if (email != null) {
            this.email = email;
        }
        if (address != null) {
            this.address = address;
        }
        if (profileImage != null) {
            this.profileImage = profileImage;
        }
    }

    public MemberResponse toResponse() {
        return MemberResponse.builder()
            .member(this)
            .build();
    }
}
