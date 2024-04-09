package com.thuctaptotnghiem.thuctaptotnghiep.controller;

import com.thuctaptotnghiem.thuctaptotnghiep.entity.UserEntity;
import com.thuctaptotnghiem.thuctaptotnghiep.exception.UserAlreadyExistsException;
import com.thuctaptotnghiem.thuctaptotnghiep.model.request.LoginRequest;
import com.thuctaptotnghiem.thuctaptotnghiep.model.request.RegisterRequest;
import com.thuctaptotnghiem.thuctaptotnghiep.model.response.LoginResponse;
import com.thuctaptotnghiem.thuctaptotnghiep.security.jwt.JwtUtils;
import com.thuctaptotnghiem.thuctaptotnghiep.security.service.UserDetailsImpl;
import com.thuctaptotnghiem.thuctaptotnghiep.security.service.UserDetailsServiceImpl;
import com.thuctaptotnghiem.thuctaptotnghiep.service.users.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/auth/")
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;
    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;
    private final UserDetailsServiceImpl userDetailsService;

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody @Valid RegisterRequest registerRequest) {
        try {
            userService.registerUser(registerRequest);
            return ResponseEntity.ok("Registration successful!");
        }catch (UserAlreadyExistsException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody @Valid LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginRequest.getEmail(),
                loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtUtils.generateJwtTokenForUser(authentication);
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority).toList();

        return ResponseEntity.ok(new LoginResponse(
                userDetails.getId(),
                userDetails.getEmail(),
                token,
                roles));

    }

}
