package com.project.app.exception;

import com.project.app.enumeration.ErrorCode;

public class DataNotFoundException extends CustomErrorException {

    public DataNotFoundException(ErrorCode errorCode) {
        super(errorCode);
    }
    public DataNotFoundException(String message, ErrorCode errorCode) {
        super(message,errorCode);
    }
}
