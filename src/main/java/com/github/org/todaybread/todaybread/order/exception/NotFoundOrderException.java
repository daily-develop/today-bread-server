package com.github.org.todaybread.todaybread.order.exception;

import graphql.GraphQLException;

public class NotFoundOrderException extends GraphQLException {

    public NotFoundOrderException() {
        super("주문 기록을 찾을 수 없습니다.");
    }
}
