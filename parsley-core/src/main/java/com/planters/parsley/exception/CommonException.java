package com.planters.parsley.exception;

import com.planters.parsley.constant.ResponseCode;
import lombok.Getter;

@Getter
public class CommonException extends RuntimeException {

    private final ResponseCode responseCode;

    public CommonException(ResponseCode responseCode) {
        this.responseCode = responseCode;
    }
}
