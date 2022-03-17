package com.planters.parsley.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ResponseCode {
    SUCCESS("0000", "Success."),

    ACCOUNT_NOT_EXISTED("1100", "Account Not Existed."),
    ACCOUNT_EMAIL_DUPLICATED("1101", "Account Email Duplicated"),
    INVALID_PASSWORD("1102", "Invalid Password"),
    PERMISSION_DENIED("1103","Permission Denied"),

    INVALID_JWT_TOKEN("2100", "Invalid Jwt Token"),
    JWT_TOKENS_NOT_MATCHED("2101", "JWT Tokens Not Matched"),


    INVALID_NEWS_CATEGORY("3100", "Invalid News Category"),
  
    CONSTRAINT_VIOLATION("9995", "Constraint Violated"),
    METHOD_ARGUMENT_NOT_VALID("9996", "Method Argument Not Valid"),
    HTTP_CLIENT_ERROR("9997", "Http Client Error"),
    UNAUTHORIZED_ERROR("9998", "Unauthorized Error"),
    UNKNOWN_ERROR("9999", "Unknown Error");

    private final String code;
    private final String message;
}
