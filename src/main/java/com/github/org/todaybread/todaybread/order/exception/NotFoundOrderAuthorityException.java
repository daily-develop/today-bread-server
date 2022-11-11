package com.github.org.todaybread.todaybread.order.exception;

import graphql.GraphQLException;

public class NotFoundOrderAuthorityException extends GraphQLException {

    public NotFoundOrderAuthorityException() {
        super("해당 주문 기록의 조회 권한을 찾을 수 없습니다.");
    }

}
