package com.github.org.todaybread.todaybread.manager.infra.persistence;

import com.github.org.todaybread.todaybread.manager.domain.Manager;
import com.github.org.todaybread.todaybread.manager.domain.ManagerRepository;
import com.github.org.todaybread.todaybread.member.domain.Member;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class ManagerRepositoryImpl implements ManagerRepository {

    private final ManagerJpaRepository managerRepository;

    @Override
    public Manager save(Manager manager) {
        return managerRepository.save(manager);
    }

    @Override
    public List<Manager> getByMember(Member member) {
        return managerRepository.findByMember(member);
    }

    @Override
    public Optional<Manager> getById(String managerId) {
        return managerRepository.findById(UUID.fromString(managerId));
    }

    @Override
    public Optional<Manager> getByStoreId(String storeId) {
        return managerRepository.findByStoreId(UUID.fromString(storeId));
    }

    @Override
    public Optional<Manager> getByMemberAndStoreId(Member member, String storeId) {
        return managerRepository.findByMemberAndStoreId(member, UUID.fromString(storeId));
    }
}
