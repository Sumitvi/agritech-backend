package com.example.bakend.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig {


    @Autowired
    private JwtFilter jwtFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http.csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth

                        // Public Auth APIs (VERY IMPORTANT)
                        .requestMatchers("/api/v1/auth/register").permitAll()
                        .requestMatchers("/api/v1/auth/login").permitAll()
                        .requestMatchers("/api/v1/auth/**").permitAll()

                        // Farmer APIs
                                .requestMatchers("/api/v1/farmers/**").hasAuthority("ROLE_FARMER")
                                .requestMatchers("/api/v1/trades/sell").hasAuthority("ROLE_FARMER")
                                .requestMatchers("/api/v1/trades/farmer/**").hasAuthority("ROLE_FARMER")
                                .requestMatchers("/api/v1/contractors/search")
                                .hasAuthority("ROLE_FARMER")
                        // Trader APIs
                                .requestMatchers("/api/v1/trades/buy").hasAuthority("ROLE_TRADER")
                                .requestMatchers("/api/v1/trades/trader/**").hasAuthority("ROLE_TRADER")

                        // Admin / Govt APIs
                        .requestMatchers("/api/v1/admin/**").hasAnyAuthority("ROLE_ADMIN", "ROLE_GOVT")
//                        .requestMatchers("/api/v1/market/**").hasAnyAuthority("ROLE_ADMIN", "ROLE_GOVT")
                                .requestMatchers("/api/v1/analytics/**")
                                .hasAnyAuthority("ROLE_ADMIN", "ROLE_GOVT")
                                .requestMatchers("/api/v1/contractors/**")
                                .hasAnyAuthority("ROLE_ADMIN", "ROLE_GOVT")


                                // Store Owner APIs
                                .requestMatchers("/api/v1/store/product/**")
                                .hasAuthority("ROLE_STORE_OWNER")
                                .requestMatchers("/api/v1/orders/*/ship")
                                .hasAnyAuthority("ROLE_STORE_OWNER", "ROLE_ADMIN")
                                .requestMatchers("/api/v1/orders/*/deliver")
                                .hasAnyAuthority("ROLE_STORE_OWNER", "ROLE_ADMIN")
// Farmer browsing


                                .requestMatchers("/api/v1/store/products")
                                .hasAuthority("ROLE_FARMER")
                                .requestMatchers("/api/v1/orders/my")
                                .hasAuthority("ROLE_FARMER")

                                .requestMatchers("/api/v1/cart/**").hasAuthority("ROLE_FARMER")
                                .requestMatchers("/api/v1/orders/**").hasAuthority("ROLE_FARMER")

                                .requestMatchers("/api/v1/store/admin/**")
                                .hasAnyAuthority("ROLE_ADMIN", "ROLE_GOVT")
                        // Everything else
                        .anyRequest().authenticated()
                )
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }
}
