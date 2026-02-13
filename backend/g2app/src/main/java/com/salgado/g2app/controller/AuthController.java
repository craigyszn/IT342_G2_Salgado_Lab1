package com.salgado.g2app.controller;
 
import java.util.Optional;
 
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
 
import com.salgado.g2app.entity.UserEntity;
import com.salgado.g2app.service.AuthService;
 
@RestController
@RequestMapping("/api/auth")
public class AuthController {
 
    private final AuthService authService;
 
    public AuthController(AuthService authService) {
        this.authService = authService;
    }
 
    // REGISTER
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody UserEntity user) {
        UserEntity savedUser = authService.registerUser(user);
        return ResponseEntity.ok(savedUser);
    }
 
    // LOGIN
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserEntity loginRequest) {
 
        Optional<UserEntity> user =
                authService.authenticateUser(
                        loginRequest.getUserEmail(),
                        loginRequest.getUserPassword()
                );
 
        if (user.isPresent()) {
            return ResponseEntity.ok(user.get());
        } else {
            return ResponseEntity.status(401).body("Invalid email or password");
        }
    }
}