package com.planters.parsley.controller;

import com.planters.parsley.component.CommonResponseMaker;
import com.planters.parsley.component.CommonResponseMaker.CommonResponseEntity;
import com.planters.parsley.exception.CommonException;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@RequiredArgsConstructor
public class GlobalExceptionHandler {

    private final CommonResponseMaker commonResponseMaker;

    @ExceptionHandler(CommonException.class)
    public CommonResponseEntity handleCommonException(final CommonException e) {
        return commonResponseMaker.makeCommonResponse(e.getResponseCode());
    }

}
