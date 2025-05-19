package com.MEDCIN.g04.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import java.util.Arrays;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    private final JwtAuthenticationFilter authFilter;

    public SecurityConfig(JwtAuthenticationFilter authFilter) {
        this.authFilter = authFilter;
    }

    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();

        // Autoriser les requêtes depuis ces origines (ajoutez vos URLs frontend)
        config.setAllowedOrigins(Arrays.asList(
                "http://localhost:3000",  // React
                "http://localhost:4200",  // Angular
                "http://localhost:8080"   // Vue ou votre frontend
        ));

        // Autoriser ces méthodes HTTP
        config.setAllowedMethods(Arrays.asList(
                "GET", "POST", "PUT", "DELETE", "OPTIONS", "PATCH"
        ));

        // Autoriser ces headers
        config.setAllowedHeaders(Arrays.asList(
                "Authorization", "Cache-Control", "Content-Type",
                "X-Requested-With", "Accept", "Origin"
        ));

        // Autoriser l'envoi de cookies (important pour l'authentification)
        config.setAllowCredentials(true);

        // Configurer le maxAge pour les requêtes preflight
        config.setMaxAge(3600L);

        // Appliquer cette configuration à toutes les routes
        source.registerCorsConfiguration("/**", config);

        return new CorsFilter(source);
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf().disable()
                .cors().and()  // Active la configuration CORS
                .authorizeHttpRequests(auth -> auth
                        // Autoriser les ressources statiques et les pages publiques
                        .requestMatchers(
                                "/",
                                "/home",
                                "/login",
                                "/auth/**",
                                "/swagger-ui/**",
                                "/v3/api-docs/**",
                                "/css/**",
                                "/js/**",
                                "/images/**"
                        ).permitAll()

                        // Routes réservées à l'ADMIN
                        .requestMatchers(
                                "/admin-dashboard",
                                "/medecins/create",
                                "/medecins/update/**",
                                "/medecins/delete/**",
                                "/espaces/**"
                        ).hasAuthority("ADMIN")

                        // Routes accessibles aux ADMIN et MEDECIN
                        .requestMatchers(
                                "/medecins/**",
                                "/patients/**",
                                "/analyses/**",
                                "/diagnostics/**",
                                "/secretaires/**",
                                "/rendezvous/**"
                        ).hasAnyAuthority("ADMIN", "MEDECIN")

                        // Toutes les autres requêtes nécessitent une authentification
                        .anyRequest().authenticated()
                )
                // Configuration de la gestion de session
                .formLogin(form -> form
                        .loginPage("/login")
                        .successHandler((request, response, authentication) -> {
                            var authorities = authentication.getAuthorities();
                            String redirectUrl = "/dashboard"; // valeur par défaut

                            if (authorities.stream().anyMatch(a -> a.getAuthority().equals("ADMIN"))) {
                                redirectUrl = "/admin-dashboard";
                            } else if (authorities.stream().anyMatch(a -> a.getAuthority().equals("MEDECIN"))) {
                                redirectUrl = "/medecin-dashboard";
                            }

                            response.sendRedirect(redirectUrl);
                        })
                        .permitAll()
                )

                .logout(logout -> logout
                        .logoutSuccessUrl("/login?logout").permitAll()
                )

                // Ajout du filtre JWT avant le filtre d'authentification
                .addFilterBefore(authFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
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