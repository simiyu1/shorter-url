package com.shorterurl.authservice.config;

import com.shorterurl.authservice.security.CustomUserDetailsService;
import com.shorterurl.authservice.security.JwtAuthenticationFilter;
import com.shorterurl.authservice.security.JwtAuthorizationFilter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
public class SecurityConfig {


    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @Bean
    public JwtAuthorizationFilter jwtAuthorizationFilter() {
        return new JwtAuthorizationFilter();
    }
    

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(customUserDetailsService).passwordEncoder(passwordEncoder);
    }

    // @Bean
    // public AuthenticationManager authenticationManager(AuthenticationManagerBuilder auth) throws Exception {
    //     return auth.build();
    // }
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests((authz) -> authz
                .anyRequest().authenticated()
            )
            .httpBasic(withDefaults());
        return http.build();
    }

    // public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    //     http
    //             .authorizeHttpRequests((authz) -> authz
    //                             .requestMatchers("/api/auth/**").permitAll()
    //                             .anyRequest().authenticated()
    //             )
    //             .addFilterBefore(jwtAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class)
    //             .sessionManagement(management -> management.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
    //             .cors(withDefaults())
    //             .csrf(withDefaults());
    //     return http.build();
    // }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> {
            // Add any URL patterns you want to ignore here
            web.ignoring().requestMatchers("/ignore1", "/ignore2");
        };
    }
}

