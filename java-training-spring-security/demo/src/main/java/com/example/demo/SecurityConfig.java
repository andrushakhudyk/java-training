package com.example.demo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {
    

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/demo/login/**", "/register").permitAll()
                .requestMatchers("/demo/admin-panel").permitAll()
                .anyRequest().authenticated()
            )
            .httpBasic(Customizer.withDefaults());

            // .formLogin(from -> from
            //     .loginPage("/login")
            //     .permitAll()
            // )
            // .httpBasic(Customizer.withDefaults());
            
            http.csrf(csrf -> csrf.disable());

            http.logout(logout -> logout
                .logoutUrl("/auth/logout")
                .logoutSuccessUrl("/login?logout")
                .invalidateHttpSession(true)
                .deleteCookies("JESSIONID")
            );
        
        return http.build();
    }
}
