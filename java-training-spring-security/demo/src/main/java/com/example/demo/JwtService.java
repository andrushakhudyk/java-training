package com.example.demo;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.Map;

@Service
public class JwtService {

    // Секретная строка длиной не менее 32 символов (256 бит)
    private final String SECRET_STRING = "SuperSecretKeyForJWTTokenGeneration32BytesLong!";
    
    // Генерируем безопасный SecretKey на основе байтов нашей строки
    private final SecretKey key = Keys.hmacShaKeyFor(SECRET_STRING.getBytes());

    // Время жизни токена (например, 1 час в миллисекундах)
    private final long EXPIRATION_TIME = 3600000; 

    public String generateToken(String username, Map<String, Object> extraClaims) {
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + EXPIRATION_TIME);
        
        return Jwts.builder()
                .claims(extraClaims)
                .subject(username)
                .issuedAt(now)
                .expiration(expiryDate)
                .signWith(key) // Используем наш валидный SecretKey
                .compact();
    }

    public Claims extractAllClaims(String token) {
        try {
            return Jwts.parser()
                    .verifyWith(key) // Используем тот же SecretKey для проверки
                    .build()
                    .parseSignedClaims(token)
                    .getPayload();
        } catch (Exception e) {
            throw new RuntimeException("Ошибка при парсинге токена", e);
        }
    }

    public String extractUsername(String token) {
        return extractAllClaims(token).getSubject();
    }

    public boolean isTokenExpired(String token) {
        return extractAllClaims(token).getExpiration().before(new Date());
    }
}