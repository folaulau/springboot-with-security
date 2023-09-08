package com.folautech.security.config;

import com.folautech.security.filter.TenantFilter;
import com.folautech.security.utils.PathUtils;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(Customizer.withDefaults())
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers(PathUtils.PUBLIC_URLS).permitAll()
                        .requestMatchers(PathUtils.SWAGGER_DOC_URLS).permitAll()
                        .anyRequest().authenticated()
                );

        http.addFilterBefore(tenantFilter(), UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    // make sure it's only called once
    @Bean
    public FilterRegistrationBean<TenantFilter> tenantFilterRegistration(TenantFilter tenantFilter) {
        FilterRegistrationBean<TenantFilter> registration = new FilterRegistrationBean<>(tenantFilter);
        registration.setEnabled(false);
        return registration;
    }

    @Bean
    public TenantFilter tenantFilter(){
        return new TenantFilter();
    }
}
