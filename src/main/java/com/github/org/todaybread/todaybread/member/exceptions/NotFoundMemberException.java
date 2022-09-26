package com.github.org.todaybread.todaybread.member.exceptions;

import graphql.GraphQLException;

public class NotFoundMemberException extends GraphQLException {

    public NotFoundMemberException() {
        super("유저를 찾을 수 없습니다.");
    }
}
