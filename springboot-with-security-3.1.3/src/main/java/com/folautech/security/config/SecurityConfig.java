package com.folautech.security.config;
import com.folautech.security.filter.AuthorizationOncePerRequestFilter;
import com.folautech.security.utils.PathUtils;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
@EnableMethodSecurity(securedEnabled = true, prePostEnabled = false)
@Configuration
@EnableWebSecurity
public class SecurityConfig {

//    @Bean
//    public AuthorizationOncePerRequestFilter authorizationOncePerRequestFilter(){
//        return new AuthorizationOncePerRequestFilter();
//    }

//    @Bean
//    public AfterFilter afterFilter(){
//        return new AfterFilter();
//    }



    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .cors(cors -> cors.disable())
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll()
                        .anyRequest().authenticated()
                )
                .httpBasic(Customizer.withDefaults());

        http.addFilterBefore(new AuthorizationOncePerRequestFilter(), org.springframework.security.web.access.intercept.AuthorizationFilter.class);

        return http.build();
    }

    // make sure it's only called once
//    @Bean
//    public FilterRegistrationBean<AuthorizationFilter> authorizationFilterRegistration() {
//        FilterRegistrationBean<AuthorizationFilter> registration = new FilterRegistrationBean<>(authorizationFilter);
//        registration.setEnabled(false);
//        return registration;
//    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring().requestMatchers(PathUtils.PUBLIC_URLS).requestMatchers(PathUtils.SWAGGER_DOC_URLS);
    }
}
