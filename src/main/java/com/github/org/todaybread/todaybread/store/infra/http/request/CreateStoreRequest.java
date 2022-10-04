package com.github.org.todaybread.todaybread.store.infra.http.request;

import com.github.org.todaybread.todaybread.manager.infra.http.request.CreateManagerRequest;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Builder
public class CreateStoreRequest {

    MultipartFile image;

    @NotEmpty
    String name;

    String description;

    @NotEmpty
    String location;

    @NotEmpty
    String phone;

    @NotNull
    CreateManagerRequest manager;
}
