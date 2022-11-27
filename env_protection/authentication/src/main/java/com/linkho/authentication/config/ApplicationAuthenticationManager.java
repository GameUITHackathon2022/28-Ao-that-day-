package com.linkho.authentication.config;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
@Slf4j
public class ApplicationAuthenticationManager implements AuthenticationManager {

    private final UserDetailsService userDetailsService;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        try
        {
            final UserDetails userDetail = userDetailsService.loadUserByUsername(authentication.getName());
            if (!passwordEncoder.matches(authentication.getCredentials().toString(), userDetail.getPassword())) {
                throw new BadCredentialsException("Wrong password");
            }
            return new UsernamePasswordAuthenticationToken(userDetail.getUsername(), userDetail.getPassword(), userDetail.getAuthorities());
        }
        catch (Exception e)
        {
            log.info(e.getMessage());
            throw e;
        }
    }

}
