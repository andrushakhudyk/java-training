package com.example.demo;

import io.jsonwebtoken.Claims;
import org.springframework.web.bind.annotation.*;
import jakarta.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/demo")
public class JwtDemoApp {

    private final UserRepository userRepository;
    private final JwtService jwtService;

    public JwtDemoApp(UserRepository userRepository, JwtService jwtService) {
        this.userRepository = userRepository;
        this.jwtService = jwtService;
    }

    @PostConstruct
    public void initData() {
        // Проверяем, если админа еще нет в базе, то создаем его
        if (userRepository.findByUsername("admin").isEmpty()) {
            User admin = new User("admin", "admin123", "ADMIN");
            userRepository.save(admin);
        }

        // Проверяем, если обычного юзера еще нет, создаем его
        if (userRepository.findByUsername("user").isEmpty()) {
            User user = new User("user", "user123", "USER");
            userRepository.save(user);
        }
    }

    @GetMapping("/login/{username}")
    public String login(@PathVariable String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Пользователь не найден"));

        Map<String, Object> extraClaims = new HashMap<>();
        extraClaims.put("role", user.getRole());

        return jwtService.generateToken(user.getUsername(), extraClaims);
    }

    @GetMapping("/admin-panel")
    public String adminPanel(@RequestParam String token) {
        if (jwtService.isTokenExpired(token)) {
            return "Токен истек";
        }
        return "Добро пожаловать в админ-панель!";
    }
}