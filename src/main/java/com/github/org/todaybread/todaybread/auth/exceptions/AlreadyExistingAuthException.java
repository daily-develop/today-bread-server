package com.github.org.todaybread.todaybread.auth.exceptions;

public class AlreadyExistingAuthException extends IllegalStateException {

    public AlreadyExistingAuthException() {
        super("회원정보가 이미 존재합니다.");
    }
}
