package com.user.user_service.dtos;

import com.user.user_service.entity.User;

public record AuthResponse(
        String token,
        User user
) {
}
