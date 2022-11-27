package com.linkho.authentication.controller;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.env_protection.jwt.JwtToken;
import com.env_protection.jwt.JwtUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.linkho.authentication.dto.LoginRequestDto;
import com.linkho.authentication.dto.SignUpRequestDto;
import com.linkho.authentication.exception.AppUserNotFoundException;
import com.linkho.authentication.exception.InvalidUsernameOrPasswordException;
import com.linkho.authentication.exception.RefreshTokenMissingException;
import com.linkho.authentication.model.AppUser;
import com.linkho.authentication.model.Role;
import com.linkho.authentication.service.AppUserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.http.HttpStatus.*;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.http.MediaType.TEXT_PLAIN;

/**
 * com.linkho.authentication.com.env_protection.event.controller
 * Created by NhatLinh - 19127652
 * Date 11/26/2022 - 12:48 AM
 * Description: ...
 */
@RestController
@RequestMapping("api/v1/account")
@AllArgsConstructor
@Slf4j
public class AuthenticationController {

    private final AppUserService appUserService;
    @PostMapping("/signup")
    @CrossOrigin
    public ResponseEntity<?> signUpAccount(@RequestBody SignUpRequestDto request) {
        try {
            var createdUser = appUserService.save(AppUser
                    .builder()
                    .username(request.getEmail())
                    .password(request.getPassword())
                    .build(), "OrdinaryUser");
            if (createdUser == null)
                return ResponseEntity
                        .status(INTERNAL_SERVER_ERROR)
                        .body("Could not create the user");
            return ResponseEntity
                    .status(CREATED)
                    .body(AppUser.builder()
                            .id(createdUser.getId())
                            .username(createdUser.getUsername())
                            .build());
        }
        catch (Exception exception)
        {
            return ResponseEntity
                    .status(BAD_REQUEST)
                    .body(exception.getMessage());
        }
    }

    @PostMapping("/signupPublisher")
    public ResponseEntity<?> signUpPublisher(@RequestBody SignUpRequestDto request) {
        try {
            var createdUser = appUserService.save(AppUser
                    .builder()
                    .username(request.getEmail())
                    .password(request.getPassword())
                    .build(), "Publisher");
            if (createdUser == null)
                return ResponseEntity
                        .status(INTERNAL_SERVER_ERROR)
                        .body("Could not create the user");
            return ResponseEntity
                    .status(CREATED)
                    .body(AppUser.builder()
                            .id(createdUser.getId())
                            .username(createdUser.getUsername())
                            .build());
        }
        catch (Exception exception)
        {
            return ResponseEntity
                    .status(BAD_REQUEST)
                    .body(exception.getMessage());
        }
    }

    @PostMapping("/login")
    @CrossOrigin
    public ResponseEntity<?> login(@RequestBody LoginRequestDto request) {
        try {
            AppUser authentication = appUserService.authenticate(request.getEmail(), request.getPassword());
            if (authentication != null)
            {
                List<String> roles = authentication.getRoles().stream().map(Role::getName).collect(Collectors.toList());
                JwtToken tokens = new JwtToken(JwtUtil.createAccessToken(authentication.getUsername(), roles),
                        JwtUtil.createRefreshToken(authentication.getUsername(), roles), request.getEmail(), roles.get(0));
                return ResponseEntity
                        .status(OK)
                        .body(tokens);
            }
            return ResponseEntity
                    .status(INTERNAL_SERVER_ERROR)
                    .build();
        }
        catch (AppUserNotFoundException userNotFoundException)
        {
            return ResponseEntity
                    .status(NOT_FOUND)
                    .body(request.getEmail());
        }
        catch (InvalidUsernameOrPasswordException invalidUsernameOrPasswordException)
        {
            return ResponseEntity
                    .status(BAD_REQUEST)
                    .body(request.getEmail());

        }
    }

    @GetMapping("/refresh")
    public void refreshToken(HttpServletRequest request, HttpServletResponse response)
            throws IOException, RefreshTokenMissingException {
        String authorizationHeader = request.getHeader(AUTHORIZATION);
        if (authorizationHeader != null && authorizationHeader.startsWith(JwtUtil.BEARER)){
            try {
                String token = authorizationHeader.substring(JwtUtil.BEARER.length());
                DecodedJWT decodedJWT = JwtUtil.getDecoded(token);
                String username = decodedJWT.getSubject();
                AppUser appUser = appUserService.findByUsername(username);
                JwtToken tokens = new JwtToken(JwtUtil.createAccessToken(appUser.getUsername(),
                        appUser.getRoles().stream().map(Role::toString).collect(Collectors.toList())),
                        token, username, appUser.getRoles().stream().findFirst().get().getName());
                response.setContentType(APPLICATION_JSON_VALUE);
                new ObjectMapper().writeValue(response.getOutputStream(), tokens);

            } catch (Exception e){
                log.error("Error refreshing token: {}", e.getMessage());
                response.setHeader("error","Error refreshing token: "+e.getMessage());
                response.setStatus(FORBIDDEN.value());
                Map<String, String> error = new HashMap<>();
                error.put("error", "Verification error");
                response.setContentType(APPLICATION_JSON_VALUE);
                new ObjectMapper().writeValue(response.getOutputStream(), error);
            }
        }else{
            throw new RefreshTokenMissingException();
        }
    }
}
