package com.shorterurl.authservice.security;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private AuthenticationManager authenticationManager;
    private static final String HEADER_AUTHORIZATION = "Authorization";
    private static final String TOKEN_PREFIX = "Bearer ";

    public JwtAuthenticationFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
        setFilterProcessesUrl("/api/auth/login"); // Set custom authentication URL if needed
    }

    @Override
    public Authentication attemptAuthentication(jakarta.servlet.http.HttpServletRequest request, jakarta.servlet.http.HttpServletResponse response)
            throws AuthenticationException {
                String header = request.getHeader(HEADER_AUTHORIZATION);
        if (header == null || !header.startsWith(TOKEN_PREFIX)) {
            throw new RuntimeException("JWT Token is missing");
        }

        String jwtToken = header.replace(TOKEN_PREFIX, "");
        try {
            Claims claims = Jwts.parser()
                    .setSigningKey("YOUR_SECRET_KEY")
                    .parseClaimsJws(jwtToken)
                    .getBody();

            String username = claims.getSubject();
            List<String> roles = (List<String>) claims.get("roles");

            if (username != null) {
                List<SimpleGrantedAuthority> authorities = roles.stream()
                        .map(role -> new SimpleGrantedAuthority(role))
                        .collect(Collectors.toList());

                Authentication auth = new UsernamePasswordAuthenticationToken(username, null, authorities);
                SecurityContextHolder.getContext().setAuthentication(auth);
                return auth;
            }
        } catch (Exception e) {
            throw new RuntimeException("Invalid JWT token");
        }
        return null;
    }

    @Override
    protected void successfulAuthentication(jakarta.servlet.http.HttpServletRequest request, jakarta.servlet.http.HttpServletResponse response, jakarta.servlet.FilterChain chain,
                                            Authentication authResult) throws IOException, jakarta.servlet.ServletException {
        // Implement successful authentication logic here
    }

    @Override
    protected void unsuccessfulAuthentication(jakarta.servlet.http.HttpServletRequest request, jakarta.servlet.http.HttpServletResponse response,
                                              AuthenticationException failed) throws IOException, jakarta.servlet.ServletException {
        // Implement unsuccessful authentication logic here
    }
}
