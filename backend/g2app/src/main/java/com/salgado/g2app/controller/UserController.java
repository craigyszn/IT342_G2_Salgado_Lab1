package com.salgado.g2app.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.salgado.g2app.service.AuthService;


@RestController
@RequestMapping("/api/user")
public class UserController {
    private final AuthService authService;
    public UserController(AuthService authService) {
        this.authService = authService;
    }
    // GET CURRENT USER PROFILE
    @GetMapping("/me")
    public ResponseEntity<?> getProfile(@RequestParam Long userId) {
        return authService.getUserProfile(userId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}