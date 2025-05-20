package com.revolution.admin.service.infrastructure.database;

import com.revolution.admin.service.api.dto.UserDto;
import com.revolution.admin.service.api.port.UserRespository;
import com.revolution.admin.service.api.query.UserFilterQuery;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserRepositoryAdapter implements UserRespository {

    private final UserJpaRepository userJpaRepository;
    private final UserMapper userMapper;

    @Override
    public UserDto save(final UserDto dto) {
        return userMapper.toDto(
                userJpaRepository.save(userMapper.toEntity(dto)
                )
        );
    }

    @Override
    public Optional<UserDto> findByUserId(final long userId) {
        return userJpaRepository.findByUserId(userId)
                .map(userMapper::toDto);
    }

    @Override
    public Page<UserDto> findAll(final Pageable pageable, final UserFilterQuery filterQuery) {
        return userJpaRepository.findAll(pageable, filterQuery.username(), filterQuery.isBlocked());
    }
}
