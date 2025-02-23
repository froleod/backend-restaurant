package by.froleod.backend_restaurant.domain.auth.controller;

import by.froleod.backend_restaurant.domain.auth.dto.AuthResponse;
import by.froleod.backend_restaurant.domain.auth.dto.LoginRequest;
import by.froleod.backend_restaurant.domain.auth.dto.RegisterRequest;
import by.froleod.backend_restaurant.domain.auth.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@Tag(name = "Аутентификация")
public class AuthController {

    private final AuthService authService;

    @Operation(summary = "Регистрация пользователя")
    @PostMapping("/register")
    public AuthResponse register(@Validated @RequestBody RegisterRequest request) {
        return authService.register(request);
    }

    @Operation(summary = "Авторизация пользователя")
    @PostMapping("/login")
    public AuthResponse login(@RequestBody LoginRequest request) {
        return authService.login(request);
    }
}