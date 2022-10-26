package com.github.org.todaybread.todaybread.manager.infra.persistence;

import com.github.org.todaybread.todaybread.manager.domain.Manager;
import com.github.org.todaybread.todaybread.member.domain.Member;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ManagerJpaRepository extends JpaRepository<Manager, UUID> {

    List<Manager> findByMemberId(UUID memberId);

    Optional<Manager> findByStoreId(UUID storeId);

    Optional<Manager> findByMemberAndStoreId(Member member, UUID storeId);
}
