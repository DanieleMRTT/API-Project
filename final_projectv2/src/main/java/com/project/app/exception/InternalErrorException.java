package com.project.app.exception;


import com.project.app.enumeration.ErrorCode;

public class InternalErrorException extends CustomErrorException {

    public InternalErrorException(String message, ErrorCode errorCode) {
        super(message, errorCode);
    }
    public InternalErrorException(String message, Throwable cause, ErrorCode errorCode) {
        super(message, cause, errorCode);
    }
}
