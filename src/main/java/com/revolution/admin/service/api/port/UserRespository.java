package com.revolution.admin.service.api.port;

import com.revolution.admin.service.api.dto.UserDto;
import com.revolution.admin.service.api.query.UserFilterQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface UserRespository {
    UserDto save(UserDto dto);

    Optional<UserDto> findByUserId(long userId);

    Page<UserDto> findAll(Pageable pageable, UserFilterQuery filterQuery);
}
