package com.github.org.todaybread.todaybread.store.application.service;

import com.github.org.todaybread.todaybread.store.domain.Store;
import com.github.org.todaybread.todaybread.store.exceptions.NotFoundStoreException;
import com.github.org.todaybread.todaybread.store.infra.persistence.StoreRepositoryImpl;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class StoreServiceImpl implements StoreService {

    private final StoreRepositoryImpl storeRepository;


    @Override
    @Transactional
    public Store save(Store store) {
        return storeRepository.save(store);
    }

    @Override
    public Store getById(String storeId) {
        return storeRepository.getById(storeId).orElseThrow(NotFoundStoreException::new);
    }

    @Override
    public List<Store> getList(int page, int take, String search) {
        Pageable pageable = PageRequest.of(page - 1, take);
        return storeRepository.getByNameContaining(search, pageable);
    }

    @Override
    public Store getByManagerId(String managerId) {
        return storeRepository.getByManagerId(managerId).orElseThrow(NotFoundStoreException::new);
    }

    @Override
    public void deleteById(String storeId) {
        storeRepository.delete(storeId);
    }
}
