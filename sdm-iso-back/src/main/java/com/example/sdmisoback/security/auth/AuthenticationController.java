package com.example.sdmisoback.security.auth;

import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService service;

    @Value("${USERS_JWT_EXPIRATION}")
    private Long jwtExpiration;

    @Value("${USERS_REFRESH_EXPIRATION}")
    private Long refreshExpiration;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(
        @RequestBody RegisterRequest request
    ) {
        return ResponseEntity.ok(service.register(request));
    }
    
    @PostMapping("/authenticate")
    public ResponseEntity<?> authenticate(
        @RequestBody AuthenticationRequest request,
        HttpServletResponse response
    ) {
        try {
            AuthenticationResponse authenticationResponse = service.authenticate(request);

            // Set HTTP-only cookies for JWT and refresh token
            Cookie jwtCookie = new Cookie("jwt", authenticationResponse.getAccessToken());
            jwtCookie.setHttpOnly(true);
            jwtCookie.setMaxAge(jwtExpiration.intValue() / 1000); 
            jwtCookie.setPath("/"); 
            jwtCookie.setSecure(true);
            jwtCookie.setAttribute("SameSite", "None");
            response.addCookie(jwtCookie);

            Cookie refreshTokenCookie = new Cookie("refresh_token", authenticationResponse.getRefreshToken());
            refreshTokenCookie.setHttpOnly(true);
            refreshTokenCookie.setMaxAge(refreshExpiration.intValue() / 1000);
            refreshTokenCookie.setPath("/"); 
            refreshTokenCookie.setSecure(true);
            refreshTokenCookie.setAttribute("SameSite", "None");
            response.addCookie(refreshTokenCookie);
            
            // Return JSON response with user roles and access token
            Map<String, Object> responseBody = new HashMap<>();
            responseBody.put("role", authenticationResponse.getRole());
            responseBody.put("access_token", authenticationResponse.getAccessToken());

            return ResponseEntity.ok(responseBody);
        } catch (BadCredentialsException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(Collections.singletonMap("error", "Incorrect password"));
        } catch (UsernameNotFoundException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(Collections.singletonMap("error", e.getMessage()));
        }
    }

    @PostMapping("/refresh-token")
    public void w(
        HttpServletRequest request,
        HttpServletResponse response
    ) throws IOException {
        service.refreshToken(request, response);
    }


}
