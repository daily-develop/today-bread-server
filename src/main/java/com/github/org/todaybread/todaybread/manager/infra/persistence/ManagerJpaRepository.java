package com.github.org.todaybread.todaybread.manager.infra.persistence;

import com.github.org.todaybread.todaybread.manager.domain.Manager;
import com.github.org.todaybread.todaybread.member.domain.Member;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ManagerJpaRepository extends JpaRepository<Manager, UUID> {

    Page<Manager> findByMemberId(UUID memberId, Pageable pageable);

    Optional<Manager> findByStoreId(UUID storeId);

    Optional<Manager> findByMemberAndStoreId(Member member, UUID storeId);
}
