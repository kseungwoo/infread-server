package com.parsley.infread.dto;

import com.parsley.infread.vo.SignUpRequestVo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SignUpRequestDto {

    private String email;
    private String password;

    public SignUpRequestVo toVo() {
        return SignUpRequestVo.builder()
                .email(email)
                .password(password)
                .build();
    }
}
