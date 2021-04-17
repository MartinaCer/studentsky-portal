package com.example.controller;

import com.example.dto.JwtResponse;
import com.example.dto.LoginRequest;
import com.example.security.JwtUtils;
import com.example.security.UserDetailsImpl;
import com.example.security.UserDetailsServiceImpl;
import javax.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("auth")
public class AuthenticationController {

    private AuthenticationManager authenticationManager;
    private PasswordEncoder encoder;
    private JwtUtils jwtUtils;
    private UserDetailsServiceImpl userDetailsService;

    public AuthenticationController(AuthenticationManager authenticationManager, PasswordEncoder encoder,
            JwtUtils jwtUtils, UserDetailsServiceImpl userDetailsServiceImpl) {
        this.authenticationManager = authenticationManager;
        this.encoder = encoder;
        this.jwtUtils = jwtUtils;
        this.userDetailsService = userDetailsServiceImpl;
    }

    public AuthenticationManager getAuthenticationManager() {
        return authenticationManager;
    }

    public PasswordEncoder getEncoder() {
        return encoder;
    }

    public JwtUtils getJwtUtils() {
        return jwtUtils;
    }

    public UserDetailsServiceImpl getUserDetailsService() {
        return userDetailsService;
    }

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword());
        Authentication authentication = authenticationManager.authenticate(token);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        return ResponseEntity.ok(new JwtResponse(jwt,
                userDetails.getId(),
                userDetails.getUsername(),
                userDetails.getAuthorities().iterator().next().getAuthority()));
    }

}
