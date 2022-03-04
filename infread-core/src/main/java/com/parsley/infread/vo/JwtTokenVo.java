package com.parsley.infread.vo;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Builder
@Getter
@ToString
public class JwtTokenVo {

    private final String accessToken;
    private final String refreshToken;
}
