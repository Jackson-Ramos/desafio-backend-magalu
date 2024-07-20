package com.jcode_development.magalums.security;

import com.jcode_development.magalums.repository.UserRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class SecurityFilter extends OncePerRequestFilter {

    private final JwtTokenProvide jwtTokenProvide;
    private final UserRepository userRepository;


    public SecurityFilter(JwtTokenProvide jwtTokenProvide,
                          UserRepository userRepository) {
        this.jwtTokenProvide = jwtTokenProvide;
        this.userRepository = userRepository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        var token = jwtTokenProvide.resolveToken(request);
        if (token != null) {
            var subject = jwtTokenProvide.validateJwtToken(token);
            UserDetails user = userRepository.loadUserByLogin(subject);

            var authentication = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
        filterChain.doFilter(request, response);
    }
}
