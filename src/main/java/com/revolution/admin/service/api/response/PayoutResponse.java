package com.revolution.admin.service.api.response;

import com.revolution.common.response.OrderResponse;

public record PayoutResponse(
        String email,
        String username,
        String bankAccountNumber,
        OrderResponse orderResponse
) {
}
