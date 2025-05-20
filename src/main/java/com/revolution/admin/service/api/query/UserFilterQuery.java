package com.revolution.admin.service.api.query;

public record UserFilterQuery(
        String username,
        Boolean isBlocked
) {
}
