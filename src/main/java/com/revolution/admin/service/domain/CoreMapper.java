package com.revolution.admin.service.domain;

import com.revolution.admin.service.api.dto.PayoutDto;
import com.revolution.admin.service.api.dto.UserDto;
import com.revolution.admin.service.api.response.AdminUserResponse;
import com.revolution.common.event.RegisterEvent;

class CoreMapper {

    AdminUserResponse toResponse(UserDto dto) {
        return new AdminUserResponse(dto.userId(), dto.username(), dto.email(), dto.isBlocked());
    }

    UserDto toDto(final RegisterEvent event) {
        return new UserDto(event.userId(), event.username(), event.email(), false);
    }

    UserDto changeStatus(UserDto dto, boolean status) {
        return new UserDto(dto.userId(), dto.username(), dto.email(), status);
    }

    PayoutDto changeStatus(PayoutDto dto, boolean status) {
        return new PayoutDto(dto.bankAccountNumber(), dto.orderId(), dto.receiverId(), dto.amount(), status);
    }
}
