package com.bezkoder.springjwt.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class PasswordTestRunner implements CommandLineRunner {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) {
        System.out.println("BCrypt hash for 123456:");
        System.out.println(passwordEncoder.encode("123456"));
    }
}
