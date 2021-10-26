package com.example.toiletfinderapp.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;


public class AuthenticationFilter extends OncePerRequestFilter {

    private static final String PERMISSION = "NORMAL";

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Autowired
    private JwtHelper jwtHelp;

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain chain) throws ServletException, IOException {
        String token = request.getHeader("Authorization");
        String username = null;
        if (!StringUtils.isEmpty(token)) {
            username = jwtHelp.getUsernameFromToken(token);
        }
        if (!StringUtils.isEmpty(username)) {
            try {
                boolean flag = false;
                UserDetails userDetails = userDetailsService.loadUserByUsername(username);
                Collection<? extends GrantedAuthority> authorities = userDetails.getAuthorities();
                for (GrantedAuthority ga : authorities) {
                    if (PERMISSION.equals(ga.getAuthority())) {
                        flag = true;
                    }
                }

                if (flag && userDetails.isEnabled()) {
                    UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                            userDetails, null, userDetails.getAuthorities());
                    authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(
                            request));
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                } else {
                    SecurityContextHolder.clearContext();
                }
            } catch (UsernameNotFoundException e) {
                System.err.println("username " + username + " not found");
            }
        } else {
            SecurityContextHolder.clearContext();
        }
        chain.doFilter(request, response);
    }
}
