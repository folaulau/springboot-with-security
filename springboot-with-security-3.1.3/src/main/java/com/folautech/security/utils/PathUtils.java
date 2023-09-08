package com.folautech.security.utils;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public interface PathUtils {

    String[] PING_URLS        = {"/ping", "/ping/", "/", "/csrf"};

    String[] PUBLIC_URLS      = {"/public/**"};

    String[] SWAGGER_DOC_URLS = {"/v3/api-docs/**", "/swagger-ui/**"};

    String[] AUTH_URLS        = {"/users/authenticate", "/logout"};
    
    public static boolean isPublicUrl(String fullUrl) {
        if(fullUrl==null || fullUrl.isEmpty()) {
            return false;
        }
        
        return List.of(AUTH_URLS, PUBLIC_URLS, PING_URLS, SWAGGER_DOC_URLS).stream().flatMap(Arrays::stream)
                .collect(Collectors.toList()).stream().filter( url -> fullUrl.contains(url)).count() > 0;
    }

}
