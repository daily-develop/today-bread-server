package com.github.org.todaybread.todaybread.store.infra.http.response;

import com.github.org.todaybread.todaybread.manager.infra.http.response.ManagerResponse;
import com.github.org.todaybread.todaybread.store.domain.Store;
import java.time.LocalDateTime;
import java.util.UUID;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class StoreResponse {

    UUID id;

    LocalDateTime createdAt;

    LocalDateTime updatedAt;

    String name;

    String description;

    String location;

    String phone;

    ManagerResponse manager;

    @Builder
    public StoreResponse(
        Store store
    ) {
        this.id = store.getId();
        this.createdAt = store.getCreatedAt();
        this.updatedAt = store.getUpdatedAt();
        this.name = store.getName();
        this.description = store.getDescription();
        this.location = store.getLocation();
        this.phone = store.getPhone();
        this.manager = store.getManager().toResponse();
    }
}
