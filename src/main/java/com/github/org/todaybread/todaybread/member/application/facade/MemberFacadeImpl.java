package com.github.org.todaybread.todaybread.member.application.facade;

import com.github.org.todaybread.todaybread.member.application.service.MemberServiceImpl;
import com.github.org.todaybread.todaybread.member.infra.http.response.MemberResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MemberFacadeImpl implements MemberFacade {

    private final MemberServiceImpl memberService;

    @Override
    public MemberResponse getMember(String memberId) {
        return memberService.getMember(memberId).toResponse();
    }
}
