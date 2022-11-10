package com.github.org.todaybread.todaybread.customer.exception;

import graphql.GraphQLException;

public class NotFoundCustomer extends GraphQLException {

    public NotFoundCustomer() {
        super("고객을 찾을 수 없습니다.");
    }
}
