package com.planters.parsley.service;

import com.planters.parsley.component.AccountRepositoryDecorator;
import com.planters.parsley.component.JwtTokenProvider;
import com.planters.parsley.component.PasswordEncoderDecorator;
import com.planters.parsley.domain.Account;
import com.planters.parsley.vo.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final AccountRepositoryDecorator accountRepository;
    private final PasswordEncoderDecorator passwordEncoder;
    private final JwtTokenProvider tokenProvider;

    /**
     * Sign-Up
     */
    @Transactional
    public void signUp(final SignUpRequestVo requestVo) {

        accountRepository.checkIfEmailDuplicated(requestVo.getEmail());

        final Account account = new Account(requestVo.getEmail(), passwordEncoder.encode(requestVo.getPassword()));

        accountRepository.save(account);
    }

    /**
     * Sign-In
     */
    @Transactional
    public SignInResponseVo signIn(final SignInRequestVo requestVo) {

        final Account account = accountRepository.findByEmail(requestVo.getEmail());

        passwordEncoder.checkIfPasswordValid(requestVo.getPassword(), account.getPassword());

        final JwtTokenVo jwtTokenVo = issueJwtTokens(account.getEmail());

        account.updateRefreshToken(jwtTokenVo.getRefreshToken());

        return SignInResponseVo.builder()
                .accessToken(jwtTokenVo.getAccessToken())
                .refreshToken(jwtTokenVo.getRefreshToken())
                .build();
    }

    /**
     * Sign-Out
     */
    @Transactional
    public void signOut(final UserPrincipal userPrincipal) {

        final Account account = accountRepository.findByEmail(userPrincipal.getUsername());

        account.removeDeviceToken();
        account.removeRefreshToken();
    }

    /**
     * Issue Jwt Tokens
     */
    public JwtTokenVo issueJwtTokens(final String email) {

        final String accessToken = tokenProvider.generateAccessToken(email);
        final String refreshToken = tokenProvider.generateRefreshToken();

        return JwtTokenVo.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }

    /**
     * Reissue Jwt Tokens
     */
    @Transactional
    public ReissueJwtTokensResponseVo reissueTokens(final ReissueJwtTokensRequestVo requestVo) {

        tokenProvider.checkIfTokenValid(requestVo.getRefreshToken());

        final Account account = accountRepository.findByEmail(tokenProvider.decodeToken(requestVo.getAccessToken()));

        account.checkIfRefreshTokensEqual(requestVo.getRefreshToken());

        final JwtTokenVo jwtTokenVo = issueJwtTokens(account.getEmail());

        account.updateRefreshToken(jwtTokenVo.getRefreshToken());

        return ReissueJwtTokensResponseVo.builder()
                .accessToken(jwtTokenVo.getAccessToken())
                .refreshToken(jwtTokenVo.getRefreshToken())
                .build();
    }
}
