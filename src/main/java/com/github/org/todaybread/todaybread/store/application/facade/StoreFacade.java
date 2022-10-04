package com.github.org.todaybread.todaybread.store.application.facade;

import com.github.org.todaybread.todaybread.store.infra.http.request.CreateStoreRequest;
import com.github.org.todaybread.todaybread.store.infra.http.request.UpdateStoreRequest;
import com.github.org.todaybread.todaybread.store.infra.http.response.StoreResponse;
import java.util.List;

public interface StoreFacade {

    StoreResponse create(String memberId, CreateStoreRequest request);

    StoreResponse update(String memberId, UpdateStoreRequest request);

    Boolean delete(String memberId, String storeId);

    StoreResponse get(String storeId);

    List<StoreResponse> getList(int page, int take, String search);
}
