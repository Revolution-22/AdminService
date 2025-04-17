package com.revolution.admin.service.api.dto;

public record UserDto(
        Long userId,
        String username,
        String email,
        boolean isBlocked
) {
}
