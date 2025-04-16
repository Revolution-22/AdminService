package com.revolution.admin.service.api.query;

public record PayoutFilterQuery (
        String username,
        Boolean isPaid
) {
}
