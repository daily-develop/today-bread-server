package com.github.org.todaybread.todaybread.auth.exceptions;

import graphql.GraphQLException;

public class InvalidTokenException extends GraphQLException {

    public InvalidTokenException() {
        super("유효하지 않은 토큰입니다.");
    }
}
