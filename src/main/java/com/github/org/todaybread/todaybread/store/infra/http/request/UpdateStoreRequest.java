package com.github.org.todaybread.todaybread.store.infra.http.request;

import javax.validation.constraints.NotEmpty;
import lombok.Builder;
import lombok.Getter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Builder
public class UpdateStoreRequest {

    @NotEmpty
    String storeId;

    MultipartFile image;

    String name;

    String description;

    String location;

    String phone;
}
