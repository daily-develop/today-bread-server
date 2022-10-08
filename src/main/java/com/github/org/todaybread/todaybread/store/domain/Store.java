package com.github.org.todaybread.todaybread.store.domain;

import com.github.org.todaybread.todaybread.common.domain.Core;
import com.github.org.todaybread.todaybread.file.domain.File;
import com.github.org.todaybread.todaybread.manager.domain.Manager;
import com.github.org.todaybread.todaybread.store.infra.http.response.StoreResponse;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotEmpty;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.util.Assert;
import reactor.util.annotation.Nullable;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Store extends Core {

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    @JoinColumn(name = "file_id")
    private File image;

    @NotEmpty
    private String name;

    private String description;

    @NotEmpty
    private String location;

    @NotEmpty
    private String phone;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    @JoinColumn(name = "manager_id")
    private Manager manager;

    @Builder
    public Store(
        String name,
        @Nullable String description,
        String location,
        String phone,
        Manager manager
    ) {
        Assert.hasText(name, "name must not be empty");
        Assert.hasText(location, "location must not be empty");
        Assert.hasText(phone, "phone must not be empty");
        Assert.isInstanceOf(Manager.class, manager, "manager should be instance of Member");

        this.name = name.strip();
        this.description = description;
        this.location = location;
        this.phone = phone;
        this.manager = manager;
    }

    public void updateFile(File file) {
        this.image = file;
    }

    public Store update(
        @Nullable String name,
        @Nullable String description,
        @Nullable String location,
        @Nullable String phone
    ) {
        if (name != null) {
            this.name = name;
        }
        if (description != null) {
            this.description = description;
        }
        if (location != null) {
            this.location = location;
        }
        if (phone != null) {
            this.phone = phone;
        }
        return this;
    }

    public StoreResponse toResponse() {
        return StoreResponse.builder()
            .store(this)
            .build();
    }

}
