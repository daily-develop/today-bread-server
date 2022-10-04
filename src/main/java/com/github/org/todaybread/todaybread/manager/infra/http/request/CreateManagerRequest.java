package com.github.org.todaybread.todaybread.manager.infra.http.request;

import javax.validation.constraints.NotEmpty;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CreateManagerRequest {

    @NotEmpty
    String nickname;
}
