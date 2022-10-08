package com.github.org.todaybread.todaybread.member.infra.http;

import com.github.org.todaybread.todaybread.member.application.facade.MemberFacadeImpl;
import com.github.org.todaybread.todaybread.member.infra.http.response.MemberResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
@PreAuthorize(value = "hasAuthority('GENERAL')")
public class MemberController {

    private final MemberFacadeImpl memberFacade;

    @QueryMapping
    public MemberResponse me(Authentication authentication) {
        return memberFacade.getMember(authentication.getName());
    }

    @QueryMapping
    public MemberResponse member(@Argument String memberId) {
        return memberFacade.getMember(memberId);
    }
}
