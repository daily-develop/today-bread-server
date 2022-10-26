package com.github.org.todaybread.todaybread.manager.domain;

import com.github.org.todaybread.todaybread.member.domain.Member;
import java.util.List;
import java.util.Optional;

public interface ManagerRepository {

    Manager save(Manager manager);

    List<Manager> getByMemberId(String memberId);

    Optional<Manager> getById(String managerId);

    Optional<Manager> getByStoreId(String storeId);

    Optional<Manager> getByMemberAndStoreId(Member member, String storeId);
}
