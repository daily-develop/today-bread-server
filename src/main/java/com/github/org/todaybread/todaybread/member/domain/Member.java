package com.github.org.todaybread.todaybread.member.domain;

import com.github.org.todaybread.todaybread.common.domain.Core;
import com.github.org.todaybread.todaybread.file.domain.File;
import com.github.org.todaybread.todaybread.member.infra.http.response.MemberResponse;
import javax.persistence.CascadeType;
import javax.persistence.Column;
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

    @Column(unique = true, nullable = false)
    private Integer steppayId;

    private String name;

    private String phone;

    private String email;

    private String postcode;

    private String address1;

    private String address2;

    @OneToOne(targetEntity = File.class, fetch = FetchType.LAZY, cascade = CascadeType.REMOVE, orphanRemoval = true)
    @JoinColumn(name = "file_id")
    private File profileImage;

    @Builder
    public Member(
        Integer steppayId,
        String name,
        String phone,
        String email,
        String postcode,
        String address1,
        String address2,
        File profileImage
    ) {
        Assert.hasText(name, "name must not be empty");
        Assert.hasText(phone, "phone must not be empty");
        Assert.hasText(email, "email must not be empty");
        Assert.hasText(postcode, "postcode must not be empty");
        Assert.hasText(address1, "address1 must not be empty");
        Assert.hasText(address2, "address2 must not be empty");

        this.steppayId = steppayId;
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.postcode = postcode;
        this.address1 = address1;
        this.address2 = address2;
        this.profileImage = profileImage;
    }

    public void update(
        @Nullable String name,
        @Nullable String phone,
        @Nullable String email,
        @Nullable String postcode,
        @Nullable String address1,
        @Nullable String address2,
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
        if (postcode != null) {
            this.postcode = postcode;
        }
        if (address1 != null) {
            this.address1 = address1;
        }
        if (address2 != null) {
            this.address2 = address2;
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
