package com.linkho.authentication.config;
//package com.linkho.authentication.config;
//
//import com.env_protection.jwt.JwtUtil;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.AuthenticationException;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.userdetails.User;
//
//import javax.servlet.FilterChain;
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//import java.util.HashMap;
//import java.util.Map;
//import java.util.stream.Collectors;
//
//import static com.env_protection.jwt.JwtUtil.ACCESS_TOKEN_ID;
//import static com.env_protection.jwt.JwtUtil.REFRESH_TOKEN_ID;
//import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
//
//@RequiredArgsConstructor
//@Slf4j
//public class AppAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
//
//    final AuthenticationManager authenticationManager;
//
//    @Override
//    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
//            throws AuthenticationException {
//        String username = request.getParameter(SPRING_SECURITY_FORM_USERNAME_KEY);
//        String password = request.getParameter(SPRING_SECURITY_FORM_PASSWORD_KEY);
//        log.info("Username: {}", username);
//        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(username, password);
//        return authenticationManager.authenticate(token);
//    }
//
//    @Override
//    public void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult)
//            throws IOException, ServletException {
//        User user = (User) authResult.getPrincipal();
//        String accessToken = JwtUtil.createAccessToken(user.getUsername(),
//                user.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()));
//        String refreshToken = JwtUtil.createAccessToken(user.getUsername(),
//                user.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()));
//        Map<String, String> tokens = new HashMap<>();
//        tokens.put(ACCESS_TOKEN_ID, accessToken);
//        tokens.put(REFRESH_TOKEN_ID, refreshToken);
//        response.setContentType(APPLICATION_JSON_VALUE);
//        log.info(accessToken);
//        new ObjectMapper().writeValue(response.getOutputStream(), tokens);
//    }
//}

import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
public class WebSecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        //http.csrf().disable().authorizeRequests(e -> e.antMatchers(HttpMethod.POST).permitAll());
        http.csrf().disable().authorizeRequests(e -> e.antMatchers(HttpMethod.POST).permitAll());
        return http.build();
    }
}