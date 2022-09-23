package com.github.org.todaybread.todaybread.member.application.service;

import com.github.org.todaybread.todaybread.member.domain.Member;
import com.github.org.todaybread.todaybread.member.exceptions.NotFoundMemberException;
import com.github.org.todaybread.todaybread.member.infra.persistence.MemberRepositoryImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberRepositoryImpl memberRepository;

    @Override
    public Member getMember(String memberId) {
        return memberRepository.getById(memberId)
            .orElseThrow(NotFoundMemberException::new);
    }

    @Override
    public Member saveMember(Member member) {
        return memberRepository.save(member);
    }
}
