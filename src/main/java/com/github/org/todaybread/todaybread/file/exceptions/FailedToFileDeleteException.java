package com.github.org.todaybread.todaybread.file.exceptions;

public class FailedToFileDeleteException extends RuntimeException {

    public FailedToFileDeleteException() {
        super("파일 삭제에 실패했습니다.");
    }
}
