package com.github.org.todaybread.todaybread.member.application.facade;

import com.github.org.todaybread.todaybread.member.infra.http.response.MemberResponse;

interface MemberFacade {

    MemberResponse getMember(String memberId);
}
