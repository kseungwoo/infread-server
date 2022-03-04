package com.parsley.infread.dto;

import com.parsley.infread.vo.SignInRequestVo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SignInRequestDto {

    private String email;
    private String password;

    public SignInRequestVo toVo() {
        return SignInRequestVo.builder()
                .email(email)
                .password(password)
                .build();
    }
}
