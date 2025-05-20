package com.revolution.admin.service.infrastructure.database;

import aj.org.objectweb.asm.commons.Remapper;
import com.revolution.admin.service.api.dto.UserDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

interface UserJpaRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByUserId(long userId);

    @Query(value = """
        SELECT *
        FROM users u
        WHERE (:username IS NULL OR u.username ILIKE CONCAT('%', :username, '%'))
          AND (:blocked IS NULL OR u.blocked = :blocked)
        ORDER BY u.id
        LIMIT :limit OFFSET :offset
    """, nativeQuery = true)
    Page<UserDto> findAll(Pageable pageable, String username, Boolean blocked);
}
