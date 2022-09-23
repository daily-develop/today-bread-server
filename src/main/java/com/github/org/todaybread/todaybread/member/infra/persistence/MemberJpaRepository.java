package com.github.org.todaybread.todaybread.member.infra.persistence;

import com.github.org.todaybread.todaybread.member.domain.Member;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberJpaRepository extends JpaRepository<Member, UUID> {

}
