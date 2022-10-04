package com.github.org.todaybread.todaybread.manager.infra.http.response;

import com.github.org.todaybread.todaybread.manager.domain.Manager;
import com.github.org.todaybread.todaybread.member.infra.http.response.MemberResponse;
import java.time.LocalDateTime;
import java.util.UUID;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ManagerResponse {

    UUID id;
    LocalDateTime createdAt;
    LocalDateTime updatedAt;

    MemberResponse member;
    String nickname;

    @Builder
    public ManagerResponse(
        Manager manager
    ) {
        this.id = manager.getId();
        this.createdAt = manager.getCreatedAt();
        this.updatedAt = manager.getUpdatedAt();
        this.nickname = manager.getNickname();
        this.member = manager.getMember().toResponse();
    }
}
