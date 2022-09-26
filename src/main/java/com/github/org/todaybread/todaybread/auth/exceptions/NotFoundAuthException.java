package com.github.org.todaybread.todaybread.auth.exceptions;

import graphql.GraphQLException;

public class NotFoundAuthException extends GraphQLException {

    public NotFoundAuthException() {
        super("회원정보가 존재하지 않습니다.");
    }
}
