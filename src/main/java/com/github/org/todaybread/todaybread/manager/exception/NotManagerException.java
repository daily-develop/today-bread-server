package com.github.org.todaybread.todaybread.manager.exception;

import graphql.GraphQLException;

public class NotManagerException extends GraphQLException {

    public NotManagerException() {
        super("해당 가게의 매니저 권한이 없습니다.");
    }
}
