package com.jobsearchmanager.jobsearchmanager.filter;

import com.jobsearchmanager.jobsearchmanager.service.AppUserServiceImpl;
import com.jobsearchmanager.jobsearchmanager.utils.JWTManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

@Component
public class JWTFilter extends OncePerRequestFilter {

    @Autowired
    private JWTManager jwtManager;

    @Autowired
    private AppUserServiceImpl appUserService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authorizationHeader = request.getHeader("Authorization");
        String token = null;
        String username = null;

        if (nonNull(authorizationHeader) && authorizationHeader.startsWith("Bearer ")) {
            token = authorizationHeader.substring(7);
            if (Boolean.TRUE.equals(this.jwtManager.checkTokenFormat(token))) {
                username = this.jwtManager.getUsername(token);
                if (nonNull(username) && isNull(SecurityContextHolder.getContext().getAuthentication())) {
                    UserDetails userDetails = this.appUserService.loadUserByUsername(username);
                    if (Boolean.TRUE.equals(this.jwtManager.isTokenValid(token, userDetails))) {
                        this.manageAuthenticationSuccessful(userDetails, request);
                        filterChain.doFilter(request, response);
                    } else {
                        this.manageInvalidToken(request, response);
                    }
                }
            } else {
                this.manageInvalidToken(request, response);
            }
        } else {
            filterChain.doFilter(request, response);
        }


    }

    private void manageAuthenticationSuccessful(UserDetails userDetails, HttpServletRequest request) {
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                new UsernamePasswordAuthenticationToken(
                        userDetails,
                        null,
                        userDetails.getAuthorities()
                );

        usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

        SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);

    }

    private void manageInvalidToken(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/errors/unauthorized").forward(request, response);
    }

}
