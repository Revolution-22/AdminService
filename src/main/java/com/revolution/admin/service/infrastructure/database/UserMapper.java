package com.revolution.admin.service.infrastructure.database;

import com.revolution.admin.service.api.dto.UserDto;
import org.springframework.stereotype.Component;

@Component
class UserMapper {

    UserEntity toEntity(final UserDto dto) {
        return new UserEntity(dto.userId(), dto.username(), dto.email(), dto.isBlocked());
    }

    UserDto toDto(final UserEntity entity) {
        return new UserDto(entity.getUserId(), entity.getUsername(), entity.getEmail(), entity.isBlocked());
    }
}
