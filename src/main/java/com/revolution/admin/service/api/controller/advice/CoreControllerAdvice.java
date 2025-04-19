package com.revolution.admin.service.api.controller.advice;

import com.revolution.admin.service.api.exception.PayoutNotFoundException;
import com.revolution.admin.service.api.exception.UserNotFoundException;
import com.revolution.common.exception.ErrorEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CoreControllerAdvice {

    @ExceptionHandler(PayoutNotFoundException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    ErrorEntity handlePayoutNotFoundException(PayoutNotFoundException exception ) {
        return new ErrorEntity(exception.getMessage(), 1500, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UserNotFoundException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    ErrorEntity handleUserNotFoundException(UserNotFoundException exception ) {
        return new ErrorEntity(exception.getMessage(), 1500, HttpStatus.NOT_FOUND);
    }
}
