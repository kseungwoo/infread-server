package com.planters.parsley.exception;

import com.planters.parsley.constant.ResponseCode;
import lombok.Getter;

@Getter
public class CommonException extends RuntimeException {

    private final ResponseCode code;

    public CommonException(ResponseCode code) {
        this.code = code;
    }
}
