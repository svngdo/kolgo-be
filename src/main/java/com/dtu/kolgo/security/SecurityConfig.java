package com.dtu.kolgo.security;

import com.dtu.kolgo.exception.FilterChainExceptionHandler;
import com.dtu.kolgo.model.Role;
import jakarta.annotation.Nonnull;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final FilterChainExceptionHandler filterChainExceptionHandler;
    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
    private final JwtLogoutHandler logoutHandler;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        // Enable CORS
        http.cors();

        // Disable CSRF (Cross Site Request Forgery)
        http.csrf().disable();

        // No session will be created or used by spring security
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        // Entry points
        http.authorizeHttpRequests()
                .requestMatchers(
                        "/demo",
                        "/auth/**",
                        "/user/reset_password",
                        "/user/update_password"
                ).permitAll()
                .requestMatchers(
                        "/demo/kol",
                        "/kol/**"
                ).hasAuthority(Role.KOL.getAuthority())
                .requestMatchers(
                        "/demo/enterprise",
                        "/enterprise/**"
                ).hasAuthority(Role.ENTERPRISE.getAuthority())
                // Disallow everything else..
                .anyRequest().authenticated();

        // If a user try to access a resource without having enough permissions
        http.exceptionHandling().authenticationEntryPoint(jwtAuthenticationEntryPoint);

        // Apply JWT
        http.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

        // Apply filter chain exception handler
        http.addFilterBefore(filterChainExceptionHandler, LogoutFilter.class);

        // Handling logout
        http.logout()
                .logoutUrl("/logout")
                .addLogoutHandler(logoutHandler);

        return http.build();
    }

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(@Nonnull CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedOrigins("*")
                        .allowedMethods("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS")
                        .allowedHeaders("Authorization", "content-type", "x-auth-token")
                        .exposedHeaders("x-auth-token");
            }
        };
    }
}
