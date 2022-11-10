package com.github.org.todaybread.todaybread.manager.domain;

import com.github.org.todaybread.todaybread.member.domain.Member;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ManagerRepository {

    Manager save(Manager manager);

    Page<Manager> getByMemberId(String memberId, Pageable pageable);

    Optional<Manager> getById(String managerId);

    Optional<Manager> getByStoreId(String storeId);

    Optional<Manager> getByMemberAndStoreId(Member member, String storeId);
}
