package com.parsley.infread.exception;

import com.parsley.infread.constant.ResponseCode;
import lombok.Getter;

@Getter
public class CommonException extends RuntimeException {

    private final ResponseCode code;

    public CommonException(ResponseCode code) {
        this.code = code;
    }
}
