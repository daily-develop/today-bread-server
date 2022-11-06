package com.github.org.todaybread.todaybread.review.exception;

import graphql.GraphQLException;

public class NotWriterException extends GraphQLException {

    public NotWriterException() {
        super("글에 대한 권한이 존재하지 않습니다.");
    }
}
