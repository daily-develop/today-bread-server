package com.github.org.todaybread.todaybread.member.exceptions;

public class NotFoundMemberException extends RuntimeException {

	public NotFoundMemberException() {
		super("유저를 찾을 수 없습니다.");
	}
}
