package com.github.org.todaybread.todaybread.file.exceptions;

import graphql.GraphQLException;

public class FailedToFileUploadException extends GraphQLException {

    public FailedToFileUploadException() {
        super("파일 업로드에 실패했습니다.");
    }
}
