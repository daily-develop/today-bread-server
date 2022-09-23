package com.github.org.todaybread.todaybread.member.domain;

import java.util.Optional;

public interface MemberRepository {

    Optional<Member> getById(String memberId);

    Member save(Member member);
}
