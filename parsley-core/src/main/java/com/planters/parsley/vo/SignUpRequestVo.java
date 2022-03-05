package com.planters.parsley.vo;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class SignUpRequestVo {

    private final String email;
    private final String password;
}
