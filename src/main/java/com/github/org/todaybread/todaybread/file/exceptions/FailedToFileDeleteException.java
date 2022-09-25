package com.github.org.todaybread.todaybread.file.exceptions;

import graphql.GraphQLException;

public class FailedToFileDeleteException extends GraphQLException {

    public FailedToFileDeleteException() {
        super("파일 삭제에 실패했습니다.");
    }
}
