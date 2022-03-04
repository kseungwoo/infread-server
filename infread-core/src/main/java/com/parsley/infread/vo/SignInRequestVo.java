package com.parsley.infread.vo;

import lombok.Builder;
import lombok.Getter;

import java.time.ZonedDateTime;

@Getter
@Builder
public class SignInRequestVo {

    private final String email;
    private final String password;
}
