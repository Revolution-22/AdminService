package com.revolution.admin.service.api.response;

public record AdminUserResponse(
        long userId,
        String username,
        String email,
        boolean isBlocked
) {
}
