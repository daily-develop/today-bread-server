package com.github.org.todaybread.todaybread.auth.exceptions;

public class InvalidOAuthException extends IllegalStateException {

    public InvalidOAuthException() {
        super("OAuth 인증에 실패하였습니다.");
    }
}
