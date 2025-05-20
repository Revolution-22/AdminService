package com.revolution.admin.service.domain.adapter

import com.revolution.admin.service.api.dto.UserDto
import com.revolution.admin.service.api.port.UserRespository
import com.revolution.admin.service.api.query.UserFilterQuery
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.Pageable

class TestUserRepository implements UserRespository {

    protected Map<Long, UserDto> database = new HashMap<>()

    @Override
    UserDto save(final UserDto dto) {
        long id = Objects.nonNull(dto.userId()) ? dto.userId() : database.size()
        UserDto dtoWithId = new UserDto(id, dto.username(), dto.email(), dto.isBlocked())
        database.put(id, dtoWithId)
        dtoWithId
    }

    @Override
    Optional<UserDto> findByUserId(final long userId) {
        Optional.ofNullable(database.get(userId))
    }

    @Override
    Page<UserDto> findAll(final Pageable pageable, final UserFilterQuery filterQuery) {
        List<UserDto> filtered = database.values().stream()
                .filter(user -> filterQuery.username() == null || user.username().contains(filterQuery.username()))
                .filter(user -> filterQuery.isBlocked() == null || user.isBlocked().equals(filterQuery.isBlocked()))
                .toList();

        int start = (int) pageable.getOffset();
        int end = Math.min(start + pageable.getPageSize(), filtered.size());

        List<UserDto> paginated = start <= end ? filtered.subList(start, end) : List.of();

        return new PageImpl<>(paginated, pageable, filtered.size());
    }
}
