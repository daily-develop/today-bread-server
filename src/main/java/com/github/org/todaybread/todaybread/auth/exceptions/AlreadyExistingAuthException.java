package com.github.org.todaybread.todaybread.auth.exceptions;

import graphql.GraphQLException;

public class AlreadyExistingAuthException extends GraphQLException {

    public AlreadyExistingAuthException() {
        super("회원정보가 이미 존재합니다.");
    }
}
