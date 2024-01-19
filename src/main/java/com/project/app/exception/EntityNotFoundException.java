package com.project.app.exception;


import com.project.app.enumeration.ErrorCode;

public class EntityNotFoundException extends CustomErrorException {

    public EntityNotFoundException(String message, ErrorCode errorCode) {
        super(message, errorCode);
    }
}
