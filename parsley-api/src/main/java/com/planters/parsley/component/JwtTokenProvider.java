package com.planters.parsley.component;

import com.planters.parsley.constant.ResponseCode;
import com.planters.parsley.exception.CommonException;
import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

@Slf4j
@Component
public class JwtTokenProvider {

    @Value("${app.jwt.secret}")
    private String jwtSecret;
    @Value("${app.jwt.accessTokenExpirationInMilliSecond}")
    private int jwtAccessTokenExpirationInMilliSecond;
    @Value("${app.jwt.refreshTokenExpirationInMilliSecond}")
    private int jwtRefreshTokenExpirationInMilliSecond;

    public String generateAccessToken(final String subject) {

        final Date now = new Date();
        final Date expiryDate = new Date(now.getTime() + jwtAccessTokenExpirationInMilliSecond);

        return Jwts.builder()
                .setSubject(subject)
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();

    }

    public String generateRefreshToken() {

        final Date now = new Date();
        final Date expiryDate = new Date(now.getTime() + jwtRefreshTokenExpirationInMilliSecond);

        return Jwts.builder()
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();

    }

    public String getUsernameFromJwt(String token) {
        final Claims claims = Jwts.parser().setSigningKey(jwtSecret)
                .parseClaimsJws(token)
                .getBody();
        return claims.getSubject();
    }

    public boolean isTokenValid(String authToken) {
        try {
            Jwts.parser().setSigningKey(jwtSecret)
                    .parseClaimsJws(authToken);
            return true;
        } catch (SignatureException ex) {
            log.error("Invalid JWT signature");
        } catch (MalformedJwtException ex) {
            log.error("Invalid JWT token");
        } catch (ExpiredJwtException ex) {
            log.error("Expired JWT token");
        } catch (UnsupportedJwtException ex) {
            log.error("Unsupported JWT token");
        } catch (IllegalArgumentException ex) {
            log.error("JWT claims string is empty.");
        }
        return false;
    }

    public void checkIfTokenValid(String authToken) {
        if (!isTokenValid(authToken))
            throw new CommonException(ResponseCode.INVALID_JWT_TOKEN);
    }

    public String decodeToken(String authToken) {
        try {
            Claims claims = Jwts.parser().setSigningKey(jwtSecret)
                    .parseClaimsJws(authToken).getBody();
            return claims.getSubject();
        } catch (ExpiredJwtException e) {
            return e.getClaims().getSubject();
        } catch (SignatureException e) {
            throw new CommonException(ResponseCode.INVALID_JWT_TOKEN);
        }
    }


}
