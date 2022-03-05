package com.planters.parsley.dto;

import com.planters.parsley.vo.ReissueJwtTokensResponseVo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReissueJwtTokensResponseDto {

    private String accessToken;
    private String refreshToken;

    public static ReissueJwtTokensResponseDto of(ReissueJwtTokensResponseVo vo) {
        return builder()
                .accessToken(vo.getAccessToken())
                .refreshToken(vo.getRefreshToken())
                .build();
    }
}
