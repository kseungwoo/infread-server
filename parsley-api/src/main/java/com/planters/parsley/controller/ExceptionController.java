package com.planters.parsley.controller;

import com.planters.parsley.dto.CommonResponse;
import com.planters.parsley.exception.CommonException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionController {

    @ExceptionHandler(CommonException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public CommonResponse<Void> handleCommonException(CommonException e) {
        return CommonResponse.fail(e.getCode());
    }

}
