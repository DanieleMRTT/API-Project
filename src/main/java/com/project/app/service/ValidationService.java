package com.project.app.service;


import com.project.app.enumeration.ErrorCode;
import com.project.app.exception.BadRequestException;
import com.project.app.exception.DataNotValidException;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.ValidatorFactory;
import java.time.LocalDate;
import java.util.Set;

@Service
public class ValidationService {


    private final ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();

    public void doValidate(Object input) {
        Set<ConstraintViolation<Object>> constraintViolations = validatorFactory.getValidator().validate(input);
        if (!constraintViolations.isEmpty()) {
            throw new DataNotValidException(ErrorCode.DATA_NOT_VALID, constraintViolations);
        }
    }

    public static void doValidateDays(Integer days) {
        if(days < 1 || days > 16) {
            throw new BadRequestException("day number has to be less than or equal to 16", ErrorCode.DATA_NOT_VALID);
        }
    }

    public static void doValidateDate(LocalDate date) {

        if(LocalDate.now().minusMonths(3).compareTo(date) > 0) {
            throw new BadRequestException("you can check a date for max 3 months from today", ErrorCode.DATA_NOT_VALID);
        }
    }



}
