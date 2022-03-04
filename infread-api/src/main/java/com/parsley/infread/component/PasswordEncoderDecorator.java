package com.parsley.infread.component;

import com.parsley.infread.constant.ResponseCode;
import com.parsley.infread.exception.CommonException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class PasswordEncoderDecorator extends BCryptPasswordEncoder {

    public void checkIfPasswordValid(final String rawPassword, final String encodedPassword) {

        if (!super.matches(rawPassword, encodedPassword)) {
            throw new CommonException(ResponseCode.INVALID_PASSWORD);
        }
    }
}
