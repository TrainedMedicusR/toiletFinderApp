package com.example.toiletfinderapp.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component

public class BCryptUtil {

    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public BCryptUtil() {
        this.bCryptPasswordEncoder = new BCryptPasswordEncoder();
    }

    public String encode(String password) {
        return bCryptPasswordEncoder.encode(password);
    }

    public Boolean matches(String password, String hash) {
        return bCryptPasswordEncoder.matches(password, hash);
    }
}
