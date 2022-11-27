package com.env_protection.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import static java.util.Arrays.stream;

@Component
public class JwtUtil {

    public static final String SECRET = "aothatday";
    public static final String ISSUER = "aothatday";
    public static final long ACCESS_TOKEN_VALIDITY = 600000;
    public static final long REFRESH_TOKEN_VALIDITY = 604800000;
    public static final String BEARER = "Bearer "; // do not remove the space!
    public static final String CLAIMS_KEY = "ROLES";
    public static final String ACCESS_TOKEN_ID = "accessToken";
    public static final String REFRESH_TOKEN_ID = "refreshToken";


    public static Algorithm getAlgorithm() {
        return Algorithm.HMAC256(SECRET.getBytes());
    }

    public static JWTVerifier getVerifier() {
        return JWT.require(getAlgorithm()).build();
    }

    public static DecodedJWT getDecoded(String token) {
        return getVerifier().verify(token);
    }

    public static String createAccessToken(String username, List<String> roles) {
        return JWT.create()
                .withSubject(username)
                .withExpiresAt(new Date(System.currentTimeMillis() + ACCESS_TOKEN_VALIDITY))
                .withIssuer(ISSUER)
                .withIssuedAt(new Date())
                .withClaim(CLAIMS_KEY, roles)
                .sign(getAlgorithm());
    }

    public static String createRefreshToken(String username, List<String> roles) {
        return JWT.create()
                .withSubject(username)
                .withExpiresAt(new Date(System.currentTimeMillis() + REFRESH_TOKEN_VALIDITY))
                .withIssuer(ISSUER)
                .withIssuedAt(new Date())
                .withClaim(CLAIMS_KEY, roles)
                .sign(getAlgorithm());
    }

    public static UsernamePasswordAuthenticationToken getUsernamePasswordAuthenticationToken(String token)
            throws TokenExpiredException
    {
        DecodedJWT decodedJWT = getDecoded(token);
        if (decodedJWT.getExpiresAt().before(new Date())) {
            throw new TokenExpiredException(decodedJWT.getToken(), decodedJWT.getExpiresAtAsInstant());
        }
        String username = decodedJWT.getSubject();
        String[] roles = decodedJWT.getClaim(CLAIMS_KEY).asArray(String.class);
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        stream(roles).forEach(role ->
                authorities.add(new SimpleGrantedAuthority(role))
        );
        return new UsernamePasswordAuthenticationToken(username, null, authorities);
    }
}