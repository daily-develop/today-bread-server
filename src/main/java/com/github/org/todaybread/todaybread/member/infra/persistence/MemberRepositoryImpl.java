package com.github.org.todaybread.todaybread.member.infra.persistence;

import com.github.org.todaybread.todaybread.member.domain.Member;
import com.github.org.todaybread.todaybread.member.domain.MemberRepository;
import java.util.Optional;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class MemberRepositoryImpl implements MemberRepository {

    private final MemberJpaRepository memberRepository;

    @Override
    public Optional<Member> getById(String memberId) {
        return memberRepository.findById(UUID.fromString(memberId));
    }

    @Override
    public Member save(Member member) {
        return memberRepository.save(member);
    }
}
