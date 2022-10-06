package com.github.org.todaybread.todaybread.store.infra.http.response;

import com.github.org.todaybread.todaybread.file.infra.http.response.FileResponse;
import com.github.org.todaybread.todaybread.manager.infra.http.response.ManagerResponse;
import com.github.org.todaybread.todaybread.store.domain.Store;
import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class StoreResponse {

    String id;

    LocalDateTime createdAt;

    LocalDateTime updatedAt;

    String name;

    String description;

    String location;

    String phone;

    ManagerResponse manager;

    FileResponse image;

    @Builder
    public StoreResponse(
        Store store
    ) {
        this.id = store.getId().toString();
        this.createdAt = store.getCreatedAt();
        this.updatedAt = store.getUpdatedAt();
        this.name = store.getName();
        this.description = store.getDescription();
        this.location = store.getLocation();
        this.phone = store.getPhone();
        this.manager = store.getManager().toResponse();
        this.image = store.getImage() == null ? null : store.getImage().toResponse();
    }
}
