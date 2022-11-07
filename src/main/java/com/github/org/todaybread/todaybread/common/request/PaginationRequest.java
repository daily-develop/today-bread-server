package com.github.org.todaybread.todaybread.common.request;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PaginationRequest {

    protected Long page;
    protected Long take;

    public PaginationRequest(Long page, Long take) {
        this.page = page;
        this.take = take;
    }
}
