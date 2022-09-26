package com.github.org.todaybread.todaybread.auth.exceptions;

import graphql.GraphQLException;

public class NotFoundRoleException extends GraphQLException {

    public NotFoundRoleException() {
        super("권한 정보가 없습니다.");
    }
}
