package com.github.org.todaybread.todaybread.member.application.service;

import com.github.org.todaybread.todaybread.member.domain.Member;

public interface MemberService {

    Member getMember(String memberId);

    Member saveMember(Member member);
}
