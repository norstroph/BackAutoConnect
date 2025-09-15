package com.AutoConnect.AutoConnect.Config;


import com.AutoConnect.AutoConnect.Entity.Enum.Role;
import com.AutoConnect.AutoConnect.Security.JwtFilter;
import com.AutoConnect.AutoConnect.Security.JwtUtil;
import com.AutoConnect.AutoConnect.Service.CustomUserDetailsService;

import com.AutoConnect.AutoConnect.Service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@AllArgsConstructor
public class SecurityConfig {
    private final CustomUserDetailsService userDetailsService;
    private final JwtUtil jwtUtil;
    private final UserService userService;
    private final JwtFilter jwtFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http

                .csrf(csrf -> csrf.disable())
                .cors(Customizer.withDefaults())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/admin/**").hasRole(Role.ADMIN.name())
                        .requestMatchers("/technician/**").hasAnyRole(Role.ADMIN.name(),Role.TECHNICIAN.name())
                        .requestMatchers("/customers/**").hasAnyRole(Role.CUSTOMER.name(),Role.ADMIN.name(),Role.TECHNICIAN.name())
                        .requestMatchers("/garage/**","/auth/**","/api/car/**","/services/**","/appointements").permitAll()

                        .requestMatchers(   "/swagger-ui/**",
                                "/v3/api-docs/**",
                                "/swagger-resources/**",
                                "/webjars/**").permitAll()
                        .anyRequest().authenticated()
                )
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);


        return http.build();
    }


}