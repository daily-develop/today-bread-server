package com.github.org.todaybread.todaybread.review.exception;

import graphql.GraphQLException;

public class NotFoundReviewException extends GraphQLException {

    public NotFoundReviewException() {
        super("리뷰가 존재하지 않습니다.");
    }
}
