package com.github.org.todaybread.todaybread.order.exception;

import graphql.GraphQLException;

public class OrderNotAcceptedException extends GraphQLException {

    public OrderNotAcceptedException() {
        super("주문을 생성할 수 없습니다.");
    }
}
