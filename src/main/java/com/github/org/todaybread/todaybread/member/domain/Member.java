package com.github.org.todaybread.todaybread.member.domain;

import com.github.org.todaybread.todaybread.common.domain.Core;
import com.github.org.todaybread.todaybread.file.domain.File;
import com.github.org.todaybread.todaybread.member.infra.http.response.MemberResponse;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
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

    private String name;

    private String phone;

    private String email;

    private String address;

    @OneToOne(targetEntity = File.class, fetch = FetchType.LAZY, cascade = CascadeType.REMOVE, orphanRemoval = true)
    @JoinColumn(name = "file_id")
    private File profileImage;

    @Builder
    public Member(
        String name,
        String phone,
        String email,
        String address,
        File profileImage
    ) {
        Assert.hasText(name, "name must not be empty");
        Assert.hasText(phone, "phone must not be empty");
        Assert.hasText(email, "email must not be empty");
        Assert.hasText(address, "address must not be empty");

        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.profileImage = profileImage;
    }

    public void update(
        @Nullable String name,
        @Nullable String phone,
        @Nullable String email,
        @Nullable String address,
        @Nullable File profileImage
    ) {
        if (name != null) {
            this.name = name;
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
