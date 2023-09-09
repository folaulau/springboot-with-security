package com.folautech.security.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserService {

    public boolean isValidUser(String email){
        return true;
    }
}
