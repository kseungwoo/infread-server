package com.planters.parsley.component;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.planters.parsley.constant.ResponseCode;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class CommonResponseMaker {

    public <T> CommonResponseEntity makeCommonResponse(final T data, final ResponseCode responseCode) {

        final CommonResponseBody responseBody = new CommonResponseBody(data, responseCode);

        final CommonResponseEntity responseEntity =
                new CommonResponseEntity(responseBody, responseCode.getHttpStatus());

        return responseEntity;
    }

    public CommonResponseEntity makeCommonResponse(final ResponseCode responseCode) {

        final CommonResponseBody responseBody = new CommonResponseBody(responseCode);

        final CommonResponseEntity responseEntity =
                new CommonResponseEntity(responseBody, responseCode.getHttpStatus());

        return responseEntity;
    }

    public class CommonResponseEntity extends ResponseEntity<CommonResponseBody> {

        public CommonResponseEntity(final CommonResponseBody body, final HttpStatus status) {
            super(body, status);
        }
    }

    @Getter
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public class CommonResponseBody<T> {

        private final String code;
        private final String message;
        private T data;

        public CommonResponseBody(final ResponseCode responseCode) {

            this.code = responseCode.getCode();
            this.message = responseCode.getMessage();
        }

        public CommonResponseBody(final T data, final ResponseCode responseCode) {

            this.data = data;
            this.code = responseCode.getCode();
            this.message = responseCode.getMessage();
        }
    }
}
