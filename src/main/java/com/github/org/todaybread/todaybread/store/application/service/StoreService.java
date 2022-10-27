package com.github.org.todaybread.todaybread.store.application.service;

import com.github.org.todaybread.todaybread.store.domain.Store;

import java.util.List;

public interface StoreService {

    Store save(Store store);

    Store getById(String storeId);

    Store getByManagerId(String managerId);

    List<Store> getList(int page, int take, String search);

    Store getByManagerId(String managerId);

    void deleteById(String storeId);
}
