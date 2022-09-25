package com.github.org.todaybread.todaybread.file.exceptions;

import graphql.GraphQLException;

public class NotExistFileException extends GraphQLException {

    public NotExistFileException() {
        super("파일이 존재하지 않습니다.");
    }
}
