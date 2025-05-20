package com.revolution.admin.service.api.dto;

import java.math.BigDecimal;

public record PayoutDto(
        String bankAccountNumber,
        long orderId,
        long receiverId,
        BigDecimal amount,
        boolean isPaid
) {
}
