package com.user.user_service.service.impl;

import com.user.user_service.constraint.Role;
import com.user.user_service.dtos.AuthResponse;
import com.user.user_service.entity.User;
import com.user.user_service.repository.UserRepository;
import com.user.user_service.service.AuthService;
import com.user.user_service.util.JwtService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtService jwtService;

    @Transactional
    public User register(String username, String password, Role role) {
        if (userRepository.findByUsername(username).isPresent()) {
            throw new RuntimeException("Username already taken");
        }

        User user = User.builder()
                .username(username)
                .password(passwordEncoder.encode(password))
                .role(role != null ? role : Role.USER)
                .build();


        return userRepository.save(user);
    }

    public User findByUserId(UUID userId) {
        return userRepository.findById(userId).orElseThrow(() -> new RuntimeException("Cannot find user"));
    }

    public User findByUserName(String userName) {
        return userRepository.findByUsername(userName).orElseThrow(() -> new RuntimeException("Cannot find user"));
    }

    public AuthResponse login(String username, String password) {
        User user = findByUserName(username);
        if (user == null) {
            if (!passwordEncoder.matches(password, user.getPassword())) {
                throw new RuntimeException("Invalid username or password");
            }
        }
        String token = jwtService.generateToken(username);
        return new AuthResponse(token, user);
    }
}
