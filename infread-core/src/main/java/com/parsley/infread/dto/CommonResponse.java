package com.parsley.infread.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.parsley.infread.constant.ResponseCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CommonResponse<T> {
    private String code;
    private String message;
    private T data;

    public CommonResponse() {
        setCode(ResponseCode.SUCCESS.getCode());
        setMessage(ResponseCode.SUCCESS.getMessage());
    }

    public CommonResponse(ResponseCode responseCode) {
        setCode(responseCode.getCode());
        setMessage(responseCode.getMessage());
    }

    public CommonResponse(ResponseCode responseCode, String message) {
        setCode(responseCode.getCode());
        setMessage(responseCode.getMessage() + ": " + message);
    }

    public static CommonResponse<Void> fail(ResponseCode responseCode) {
        return new CommonResponse<>(responseCode);
    }

    public static CommonResponse<Void> fail(ResponseCode responseCode, String message) {
        return new CommonResponse<>(responseCode, message);
    }
}
