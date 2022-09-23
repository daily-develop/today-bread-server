package com.github.org.todaybread.todaybread.common.request;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PaginationRequest {

	protected Integer page;
	protected Integer take;

	public PaginationRequest(Integer page, Integer take) {
		this.page = page;
		this.take = take;
	}
}
