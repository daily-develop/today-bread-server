package com.github.org.todaybread.todaybread.manager.application;

import com.github.org.todaybread.todaybread.manager.domain.Manager;
import com.github.org.todaybread.todaybread.manager.exception.NotFoundManagerException;
import com.github.org.todaybread.todaybread.manager.exception.NotManagerException;
import com.github.org.todaybread.todaybread.manager.infra.http.request.UpdateManagerRequest;
import com.github.org.todaybread.todaybread.manager.infra.persistence.ManagerRepositoryImpl;
import com.github.org.todaybread.todaybread.member.domain.Member;
import com.github.org.todaybread.todaybread.store.exceptions.NotFoundStoreException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ManagerServiceImpl implements ManagerService {

    private final ManagerRepositoryImpl managerRepository;

    @Override
    @Transactional
    public Manager save(Manager manager) {
        return managerRepository.save(manager);
    }


    @Override
    @Transactional
    public Manager update(String memberId, UpdateManagerRequest request) {
        Manager manager = managerRepository.getById(request.getManagerId())
            .orElseThrow(NotFoundManagerException::new);

        return manager.update(request.getNickname());
    }

    @Override
    public List<Manager> getByMemberId(String memberId) {
        return managerRepository.getByMemberId(memberId);
    }

    @Override
    public Manager getById(String managerId) {
        return managerRepository.getById(managerId).orElseThrow(NotFoundManagerException::new);
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
