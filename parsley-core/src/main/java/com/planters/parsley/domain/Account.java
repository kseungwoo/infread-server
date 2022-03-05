package com.planters.parsley.domain;

import com.planters.parsley.constant.ResponseCode;
import com.planters.parsley.exception.CommonException;
import com.planters.parsley.vo.AccountVo;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "account")
public class Account extends AbstractAuditingEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "account_id")
    private Long id;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "refresh_token")
    private String refreshToken;

    @Column(name = "device_token")
    private String deviceToken;

    public Account(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public AccountVo toVo() {
        return AccountVo.builder()
                .id(id)
                .email(email)
                .password(password)
                .build();
    }

    public void checkIfRefreshTokensEqual(final String refreshToken) {
        if (!this.refreshToken.equals(refreshToken)) {
            throw new CommonException(ResponseCode.JWT_TOKENS_NOT_MATCHED);
        }
    }

    public void updateRefreshToken(final String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public void removeRefreshToken() {
        this.refreshToken = null;
    }

    public void removeDeviceToken() {
        this.deviceToken = null;
    }
}
