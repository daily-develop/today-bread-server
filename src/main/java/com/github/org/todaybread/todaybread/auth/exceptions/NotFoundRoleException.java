package com.github.org.todaybread.todaybread.auth.exceptions;

public class NotFoundRoleException extends RuntimeException {

	public NotFoundRoleException() {
		super("권한 정보가 없습니다.");
	}
}
