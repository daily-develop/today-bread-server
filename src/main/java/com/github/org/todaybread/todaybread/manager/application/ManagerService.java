package com.github.org.todaybread.todaybread.manager.application;

import com.github.org.todaybread.todaybread.manager.domain.Manager;
import com.github.org.todaybread.todaybread.manager.infra.http.request.UpdateManagerRequest;
import com.github.org.todaybread.todaybread.member.domain.Member;

import java.util.List;

public interface ManagerService {

    Manager save(Manager manager);

    Manager update(String memberId, UpdateManagerRequest request);

    List<Manager> getByMemberId(String memberId);

    Manager getById(String managerId);

    Manager getByStoreId(String storeId);

    void checkManagerAuthentication(Member member, String storeId);
}
