package com.planters.parsley.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ResponseCode {
    SUCCESS("0000", "Success", HttpStatus.OK),

    ACCOUNT_NOT_EXISTED("1100", "Account Not Existed.", HttpStatus.BAD_REQUEST),
    ACCOUNT_EMAIL_DUPLICATED("1101", "Account Email Duplicated", HttpStatus.BAD_REQUEST),
    INVALID_PASSWORD("1102", "Invalid Password", HttpStatus.BAD_REQUEST),
    PERMISSION_DENIED("1103", "Permission Denied", HttpStatus.BAD_REQUEST),

    INVALID_JWT_TOKEN("2100", "Invalid Jwt Token", HttpStatus.BAD_REQUEST),
    JWT_TOKENS_NOT_MATCHED("2101", "JWT Tokens Not Matched", HttpStatus.BAD_REQUEST),

    INVALID_NEWS_CATEGORY("3100", "Invalid News Category", HttpStatus.BAD_REQUEST),

    CONSTRAINT_VIOLATION("9995", "Constraint Violated", HttpStatus.BAD_REQUEST),
    METHOD_ARGUMENT_NOT_VALID("9996", "Method Argument Not Valid", HttpStatus.BAD_REQUEST),
    HTTP_CLIENT_ERROR("9997", "Http Client Error", HttpStatus.BAD_REQUEST),
    UNAUTHORIZED_ERROR("9998", "Unauthorized Error", HttpStatus.BAD_REQUEST),
    UNKNOWN_ERROR("9999", "Unknown Error", HttpStatus.BAD_REQUEST);

    private final String code;
    private final String message;
    private final HttpStatus httpStatus;
}
