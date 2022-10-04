package com.github.org.todaybread.todaybread.manager.application;

import com.github.org.todaybread.todaybread.manager.domain.Manager;
import com.github.org.todaybread.todaybread.manager.exception.NotFoundStoreException;
import com.github.org.todaybread.todaybread.manager.exception.NotManagerException;
import com.github.org.todaybread.todaybread.manager.infra.persistence.ManagerRepositoryImpl;
import com.github.org.todaybread.todaybread.member.domain.Member;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ManagerServiceImpl implements ManagerService {

    private final ManagerRepositoryImpl managerRepository;

    @Override
    @Transactional
    public Manager save(Manager manager) {
        return managerRepository.save(manager);
    }

    @Override
    public List<Manager> getByMember(Member member) {
        return managerRepository.getByMember(member);
    }

    @Override
    public Manager getByStoreId(String storeId) {
        return managerRepository.getByStoreId(storeId)
            .orElseThrow(NotFoundStoreException::new);
    }

    @Override
    public void checkManagerAuthentication(Member member, String storeId) {
        if (managerRepository.getByMemberAndStoreId(member, storeId).isEmpty()) {
            throw new NotManagerException();
        }
    }
}
