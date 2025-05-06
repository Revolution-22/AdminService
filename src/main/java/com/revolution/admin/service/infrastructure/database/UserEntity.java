package com.revolution.admin.service.infrastructure.database;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users")
@Builder
@Getter
@Entity
public class UserEntity {

    @Id
    private Long userId;

    private String username;

    private String email;

    @Column(name = "blocked")
    private boolean isBlocked;
}
