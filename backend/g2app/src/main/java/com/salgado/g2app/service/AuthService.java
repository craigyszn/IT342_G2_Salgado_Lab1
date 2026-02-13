package com.salgado.g2app.service;

import java.util.Optional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.salgado.g2app.entity.UserEntity;
import com.salgado.g2app.repository.UserRepository;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthService(UserRepository userRepository,
                       PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    // REGISTER USER
    public UserEntity registerUser(UserEntity user) {

        // Encrypt password before saving
        user.setUserPassword(passwordEncoder.encode(user.getUserPassword()));

        return userRepository.save(user);
    }

    // AUTHENTICATE LOGIN
    public Optional<UserEntity> authenticateUser(String userEmail, String rawPassword) {

        Optional<UserEntity> userOpt = userRepository.findByUserEmail(userEmail);

        if (userOpt.isPresent()) {
            UserEntity user = userOpt.get();

            // check password match
            if (passwordEncoder.matches(rawPassword, user.getUserPassword())) {
                return Optional.of(user);
            }
        }

        return Optional.empty();
    }

    // GET PROFILE
    public Optional<UserEntity> getUserProfile(Long userId) {
        return userRepository.findById(userId);
    }

    // LOGOUT (placeholder for Session 1)
    public void userLogout() {
        // No implementation needed yet
    }
}

 