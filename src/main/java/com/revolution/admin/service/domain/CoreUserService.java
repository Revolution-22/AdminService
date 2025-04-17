package com.revolution.admin.service.domain;

import com.revolution.admin.service.api.dto.UserDto;
import com.revolution.admin.service.api.exception.UserNotFoundException;
import com.revolution.admin.service.api.port.UserRespository;
import com.revolution.admin.service.api.port.UserService;
import com.revolution.admin.service.api.query.UserFilterQuery;
import com.revolution.admin.service.api.response.AdminUserResponse;
import com.revolution.common.event.RegisterEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@RequiredArgsConstructor
class CoreUserService implements UserService {

    private final UserRespository userRespository;
    private final CoreMapper userMapper;

    @Override
    public AdminUserResponse handleUserRegister(final RegisterEvent event) {
        return userMapper.toResponse(
                userRespository.save(userMapper.toDto(event))
        );
    }

    @Override
    public AdminUserResponse getUserById(final long userId) {
        return userMapper.toResponse(getById(userId));
    }
    private UserDto getById(final long userId) {
        return userRespository.findByUserId(userId)
                .orElseThrow(() -> new UserNotFoundException(userId));
    }

    @Override
    public Page<AdminUserResponse> getUsers(final Pageable pageable, final UserFilterQuery filterQuery) {
        return userRespository.findAll(pageable, filterQuery)
                .map(userMapper::toResponse);
    }

    @Override
    public AdminUserResponse changeBlockStatus(final long userId, final boolean blockStatus) {
        UserDto userDto = userMapper.changeStatus(getById(userId), blockStatus);
        return userMapper.toResponse(
                userRespository.save(userDto)
        );
    }
}
