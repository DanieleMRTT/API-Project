package com.project.app.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.ConstraintViolation;
import java.util.Set;

// Generate a constructor with all the fields of the class, in this case the constraintViolations field
@AllArgsConstructor
@Getter
public class ValidationException extends RuntimeException {

    private final Set<ConstraintViolation<Object>> constraintViolations;
}
