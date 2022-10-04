package com.github.org.todaybread.todaybread.manager.domain;

import com.github.org.todaybread.todaybread.common.domain.Core;
import com.github.org.todaybread.todaybread.manager.infra.http.response.ManagerResponse;
import com.github.org.todaybread.todaybread.member.domain.Member;
import com.github.org.todaybread.todaybread.store.domain.Store;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotEmpty;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.util.Assert;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Manager extends Core {

    @NotEmpty
    private String nickname;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id")
    private Store store = null;


    @Builder
    public Manager(String nickname, Member member) {
        Assert.hasText(nickname, "nickname must not be empty");
        Assert.isInstanceOf(Member.class, member, "member should be instance of Member");

        this.nickname = nickname.strip();
        this.member = member;
    }

    public void updateStore(Store store) {
        this.store = store;
    }

    public ManagerResponse toResponse() {
        return ManagerResponse.builder()
            .manager(this)
            .build();
    }
}
