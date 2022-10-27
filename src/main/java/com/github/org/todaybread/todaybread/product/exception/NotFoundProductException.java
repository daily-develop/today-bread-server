package com.github.org.todaybread.todaybread.product.exception;

import graphql.GraphQLException;

public class NotFoundProductException extends GraphQLException {

    public NotFoundProductException() {
        super("패키지를 찾을 수 없습니다.");
    }
}
