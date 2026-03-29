package com.user.user_service.dtos;

import com.user.user_service.constraint.Role;

public record RegisterInput(
        String userName,
        String password,
        Role role
) {
}
