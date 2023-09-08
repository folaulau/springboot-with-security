package com.folautech.security.filter;

import com.folautech.security.utils.PathUtils;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

//@Component
@Slf4j
public class AuthorizationOncePerRequestFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        log.info("AuthorizationOncePerRequestFilter.doFilterInternal...");

        List<GrantedAuthority> authorities = new ArrayList<>();

        StringBuilder requestURL = new StringBuilder(request.getRequestURL().toString());
        String queryString = request.getQueryString();

        String fullUrl = "";

        if (queryString == null) {
            fullUrl = requestURL.toString();
        } else {
            fullUrl = requestURL.append('?').append(queryString).toString();
        }

        log.info("fullUrl: {}", fullUrl);

        if(PathUtils.isPublicUrl(fullUrl)){
            log.info("public url");

            authorities.add(new SimpleGrantedAuthority("ROLE_VISITOR"));

            UsernamePasswordAuthenticationToken updateAuth = new UsernamePasswordAuthenticationToken("folau", "kaveinga", authorities);

            SecurityContextHolder.getContext().setAuthentication(updateAuth);

            filterChain.doFilter(request,response);

        }else{
            log.info("protected url");

            authorities.add(new SimpleGrantedAuthority("ROLE_USER"));

            authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));

            UsernamePasswordAuthenticationToken updateAuth = new UsernamePasswordAuthenticationToken("folau", "kaveinga", authorities);

            SecurityContextHolder.getContext().setAuthentication(updateAuth);

            filterChain.doFilter(request,response);

        }

    }
}
