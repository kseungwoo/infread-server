package com.planters.parsley.dto;

import com.planters.parsley.vo.SignInResponseVo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SignInResponseDto {

    private String accessToken;
    private String refreshToken;

    public static SignInResponseDto of(SignInResponseVo vo) {
        return builder()
                .accessToken(vo.getAccessToken())
                .refreshToken(vo.getRefreshToken())
                .build();
    }
}
