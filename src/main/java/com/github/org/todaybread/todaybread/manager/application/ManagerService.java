package com.github.org.todaybread.todaybread.manager.application;

import com.github.org.todaybread.todaybread.manager.domain.Manager;
import com.github.org.todaybread.todaybread.member.domain.Member;
import java.util.List;

public interface ManagerService {

    Manager save(Manager manager);

    List<Manager> getByMember(Member member);

    Manager getByStoreId(String storeId);

    void checkManagerAuthentication(Member member, String storeId);
}
