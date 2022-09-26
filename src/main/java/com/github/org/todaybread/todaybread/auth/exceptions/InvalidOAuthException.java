package com.github.org.todaybread.todaybread.auth.exceptions;

import graphql.GraphQLException;

public class InvalidOAuthException extends GraphQLException {

    public InvalidOAuthException() {
        super("OAuth 인증에 실패하였습니다.");
    }
}
