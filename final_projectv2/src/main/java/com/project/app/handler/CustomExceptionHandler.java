package com.project.app.handler;


import com.project.app.dto.ErrorResponseDto;
import com.project.app.enumeration.ErrorCode;
import com.project.app.exception.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {


    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<ErrorResponseDto> handleValidationException(ValidationException validationException) {
        log.error("Validation exception", validationException);
        List<String> messages = validationException.getConstraintViolations().stream()
                .map(cv -> cv.getPropertyPath().toString() + ": " + cv.getMessage())
                .collect(Collectors.toList());
        return ResponseEntity.badRequest().body(ErrorResponseDto.builder()
                .errorCode(ErrorCode.DATA_NOT_VALID)
                .errorMessage(String.join(", ", messages))
                .build());
    }

    @ExceptionHandler(DataNotFoundException.class)
    public ResponseEntity<ErrorResponseDto> handleDataNotFoundException(DataNotFoundException dataNotFoundException) {
        log.error("Data not found exception", dataNotFoundException);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ErrorResponseDto.builder()
                .errorCode(ErrorCode.DATA_NOT_FOUND)
                .errorMessage(dataNotFoundException.getMessage())
                .build());
    }

    @ExceptionHandler(DataNotValidException.class)
    public ResponseEntity<ErrorResponseDto> handleDataNotValidException(DataNotValidException exception) {

        return handleCustomErrorException(exception, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ErrorResponseDto> handleEntityNotFoundException(EntityNotFoundException exception) {
        return handleCustomErrorException(exception, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(InternalErrorException.class)
    public ResponseEntity<ErrorResponseDto> handleInternalErrorException(InternalErrorException exception) {
        return handleCustomErrorException(exception, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ErrorResponseDto> handleBadRequestException(BadRequestException badRequestException) {
        log.error("Bad request exception", badRequestException);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ErrorResponseDto.builder()
                .errorCode(badRequestException.getErrorCode())
                .errorMessage(badRequestException.getMessage())
                .build());
    }

    private ResponseEntity<ErrorResponseDto> handleCustomErrorException(CustomErrorException exception, HttpStatus httpStatus) {
        log.error("<< Managed Exception >>", exception);
        return ResponseEntity.status(httpStatus).body(
                ErrorResponseDto.builder()
                        .errorCode(exception.getErrorCode())
                        .errorMessage(exception.getMessage())
                        .build());
    }
}
