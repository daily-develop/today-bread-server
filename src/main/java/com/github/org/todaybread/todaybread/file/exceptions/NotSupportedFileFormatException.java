package com.github.org.todaybread.todaybread.file.exceptions;

import graphql.GraphQLException;

public class NotSupportedFileFormatException extends GraphQLException {

    public NotSupportedFileFormatException(String mime) {
        super(mime + " 은 지원하지 않는 파일 형식입니다.");
    }
}
