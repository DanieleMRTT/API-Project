package com.project.app.exception;

import com.project.app.enumeration.ErrorCode;
import lombok.Getter;

@Getter
public class CustomErrorException extends RuntimeException {

    private final ErrorCode errorCode;

    public CustomErrorException(ErrorCode errorCode) {
        this.errorCode = errorCode;
    }

    protected CustomErrorException(String message, ErrorCode errorCode) {
        super(message);
        this.errorCode = errorCode;
    }

    protected CustomErrorException(String message, Throwable cause, ErrorCode errorCode) {
        super(message, cause);
        this.errorCode = errorCode;
    }
}
