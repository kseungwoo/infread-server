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

    INVALID_PSYCHOLOGICAL_TEST_TYPE("3100", "Psychological Test Type Invalid"),
    INVALID_PSYCHOLOGICAL_TEST_DEGREE("3101", "Psychological Test Degree Invalid"),
    PSYCHOLOGICAL_TEST_NOT_EXISTED("3102","Psychological Test Not Existed"),
    INVALID_PSYCHOLOGICAL_QUESTION("3103","Psychological Test Question Invalid"),

    INVALID_EMOTION_TYPE("4100", "Emotion Type Invalid"),
    DAILY_CHAT_RESULT_NOT_EXISTED("4101", "Daily Chat Result Not Existed"),
    USER_MESSAGE_NOT_EXISTED("4102", "User Message Not Existed"),

    INVALID_CHAT_TYPE("5100", "Chat Type Invaild"),
    INVALID_CHAT_QUESTION("5101", "Chat Question Invaild"),
    TODAY_DAILY_CHAT_DUPLICATED("5102", "Today Daily Chat Duplicated"),

    MISSION_INFORMATION_NOT_EXISTED("6100", "Mission Information Not Existed"),
    MISSION_SUCCESS_RECORD_NOT_EXISTED("6101", "Mission Success Record Not Existed"),

    NEED_TO_UPDATE_V1("9994", "Need To Update (Now: V1)"),
  
    CONSTRAINT_VIOLATION("9995", "Constraint Violated"),
    METHOD_ARGUMENT_NOT_VALID("9996", "Method Argument Not Valid"),
    HTTP_CLIENT_ERROR("9997", "Http Client Error"),
    UNAUTHORIZED_ERROR("9998", "Unauthorized Error"),
    UNKNOWN_ERROR("9999", "Unknown Error");

    private final String code;
    private final String message;
}
