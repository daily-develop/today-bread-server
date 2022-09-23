package com.github.org.todaybread.todaybread.member.infra.http;

import com.github.org.todaybread.todaybread.member.application.facade.MemberFacadeImpl;
import com.github.org.todaybread.todaybread.member.infra.http.response.MemberResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberFacadeImpl memberFacade;

    @QueryMapping("member")
    public MemberResponse Member(@Argument String memberId) {
        return memberFacade.getMember(memberId);
    }
}
