package com.parsley.infread.vo;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class SignInResponseVo {

    private final String accessToken;
    private final String refreshToken;
}
