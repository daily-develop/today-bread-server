package com.github.org.todaybread.todaybread.store.exceptions;

import graphql.GraphQLException;

public class NotFoundStoreException extends GraphQLException {

    public NotFoundStoreException() {
        super("가게를 찾지 못했습니다.");
    }
}
