package by.froleod.backend_restaurant.domain.auth.service;

import by.froleod.backend_restaurant.domain.auth.dto.AuthResponse;
import by.froleod.backend_restaurant.domain.auth.dto.LoginRequest;
import by.froleod.backend_restaurant.domain.auth.dto.RegisterRequest;
import by.froleod.backend_restaurant.domain.user.entity.Role;
import by.froleod.backend_restaurant.domain.user.entity.User;
import by.froleod.backend_restaurant.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserService userService;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    public AuthResponse register(RegisterRequest request) {

        var user = User.builder()
                .username(request.getUsername())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.ROLE_USER)
                .build();

        userService.create(user);

        var jwt = jwtService.generateToken(user);
        return new AuthResponse(jwt);
    }

    public AuthResponse login(LoginRequest request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                request.getUsername(),
                request.getPassword()
        ));

        var user = userService
                .userDetailsService()
                .loadUserByUsername(request.getUsername());

        var jwt = jwtService.generateToken(user);
        return new AuthResponse(jwt);
    }

//    private final UserRepository userRepository;
//    private final PasswordEncoder passwordEncoder;
//    private final JwtService jwtService;
//    private final AuthenticationManager authenticationManager;

//    public AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtService jwtService, AuthenticationManager authenticationManager) {
//        this.userRepository = userRepository;
//        this.passwordEncoder = passwordEncoder;
//        this.jwtService = jwtService;
//        this.authenticationManager = authenticationManager;
//    }

//    public AuthService(PasswordEncoder passwordEncoder) {
//        this.passwordEncoder = passwordEncoder;
//    }

//    public AuthResponse register(RegisterRequest request) {
//        User user = new User();
//        user.setUsername(request.getUsername());
//        user.setEmail(request.getEmail());
//        user.setPassword(passwordEncoder.encode(request.getPassword()));
//        user.setRole("USER");
//        userRepository.save(user);
//
//        String token = jwtService.generateToken(user.getUsername());
//        return new AuthResponse(token);
//    }

//    public AuthResponse login(LoginRequest request) {
//        authenticationManager.authenticate(
//                new UsernamePasswordAuthenticationToken(
//                        request.getUsername(),
//                        request.getPassword()
//                )
//        );
//
//        User user = userRepository.findByUsername(request.getUsername())
//                .orElseThrow(() -> new RuntimeException("Пользователь не найден"));
//
//        String token = jwtService.generateToken(user.getUsername());
//        return new AuthResponse(token);
//    }

//    public AuthResponse login(LoginRequest request) {
//        try {
//            // Аутентификация пользователя
//            authenticationManager.authenticate(
//                    new UsernamePasswordAuthenticationToken(
//                            request.getUsername(),
//                            request.getPassword()
//                    )
//            );
//
//            // Поиск пользователя в базе данных
//            User user = userRepository.findByUsername(request.getUsername())
//                    .orElseThrow(() -> new RuntimeException("Пользователь не найден"));
//
//            // Генерация JWT
//            String token = jwtService.generateToken(user.getUsername());
//            return new AuthResponse(token);
//        } catch (AuthenticationException e) {
//            throw new RuntimeException("Ошибка аутентификации", e);
//        }
//    }
}