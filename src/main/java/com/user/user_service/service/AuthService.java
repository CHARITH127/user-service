package com.user.user_service.service;

import com.user.user_service.constraint.Role;
import com.user.user_service.entity.User;

import java.util.UUID;

public interface AuthService {
    User register(String username, String password, Role role);

    User findByUserId(UUID userId);

    User findByUserName(String userName);
}
