package com.project.app.exception;


import com.project.app.enumeration.ErrorCode;


public class BadRequestException extends CustomErrorException {

    public BadRequestException(String message, ErrorCode errorCode) {
        super(message, errorCode);

    }

    public BadRequestException(String message, Throwable cause, ErrorCode errorCode) {
        super(message,cause,errorCode);

    }

}
