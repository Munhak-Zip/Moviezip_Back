package com.example.moviezip.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthCheckController {

    @GetMapping("/auth-check")
    public boolean isAuthenticated(@AuthenticationPrincipal UserDetails userDetails) {
        return userDetails != null;
    }
}
