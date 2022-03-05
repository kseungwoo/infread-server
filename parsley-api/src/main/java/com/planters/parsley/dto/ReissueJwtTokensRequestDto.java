package com.planters.parsley.dto;

import com.planters.parsley.vo.ReissueJwtTokensRequestVo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReissueJwtTokensRequestDto {

    private String accessToken;
    private String refreshToken;

    public ReissueJwtTokensRequestVo toVo() {
        return ReissueJwtTokensRequestVo.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }
}
