package com.planters.parsley.vo;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ReissueJwtTokensRequestVo {

    private final String accessToken;
    private final String refreshToken;
}
