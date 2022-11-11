package com.github.org.todaybread.todaybread.customer.domain;

import com.github.org.todaybread.todaybread.common.domain.Core;
import com.github.org.todaybread.todaybread.member.domain.Member;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToOne;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Customer extends Core {

    @Column(unique = true, nullable = false)
    private Long steppayId;

    @OneToOne(targetEntity = Member.class, fetch = FetchType.LAZY)
    private Member member;

    @Builder
    public Customer(
        Long steppayId,
        Member member
    ) {
        this.steppayId = steppayId;
        this.member = member;
    }
}
