package com.example.demoreplay.controller;


import com.example.demoreplay.dto.auth.JwtAuthenticationResponse;
import com.example.demoreplay.dto.auth.SignInRequest;
import com.example.demoreplay.dto.auth.SignUpRequest;
import com.example.demoreplay.service.bonus.AuthenticationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@Tag(name = "Аутентифікація")
public class AuthController {
    private final AuthenticationService authenticationService;

    @Operation(summary = "Реєстрація користувача")
    @PostMapping("/sign-up")
    public JwtAuthenticationResponse signUp(@RequestBody @Valid SignUpRequest request) {
        return authenticationService.signUp(request);
    }

    @Operation(summary = "Авторизація користувача")
    @PostMapping("/sign-in")
    public JwtAuthenticationResponse signIn(@RequestBody @Valid SignInRequest request) {
        return authenticationService.signIn(request);
    }
}

