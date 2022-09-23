package com.github.org.todaybread.todaybread.member.infra.http.response;

import com.github.org.todaybread.todaybread.file.domain.File;
import com.github.org.todaybread.todaybread.member.domain.Member;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class MemberResponse {

    String id;

    String createdAt;

    String updatedAt;

    String name;

    String nickname;

    String phone;

    String email;

    String address;

    String profileImageUrl;

    @Builder
    public MemberResponse(
        Member member
    ) {
        this.id = member.getId().toString();
        this.createdAt = member.getCreatedAt().toString();
        this.updatedAt = member.getUpdatedAt().toString();
        this.name = member.getName();
        this.nickname = member.getNickname();
        this.phone = member.getPhone();
        this.email = member.getEmail();
        this.address = member.getAddress();
        this.profileImageUrl = "https://kr.object.ncloudstorage.com/bread/" + member.getProfileImage().getKey();
    }
}