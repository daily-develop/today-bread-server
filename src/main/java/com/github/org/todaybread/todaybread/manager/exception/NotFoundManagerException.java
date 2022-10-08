package com.github.org.todaybread.todaybread.manager.exception;

import graphql.GraphQLException;

public class NotFoundManagerException extends GraphQLException {

    public NotFoundManagerException() {
        super("매니저를 찾을 수 없습니다.");
    }
}
