package com.env_protection.jwt;

/**
 * com.linkho.authentication.jwt
 * Created by NhatLinh - 19127652
 * Date 11/26/2022 - 10:07 AM
 * Description: ...
 */
public record JwtToken(String accessToken, String refreshToken, String username, String mainRole) {
}
