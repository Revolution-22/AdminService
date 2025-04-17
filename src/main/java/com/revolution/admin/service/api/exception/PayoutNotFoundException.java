package com.revolution.admin.service.api.exception;

public class PayoutNotFoundException extends RuntimeException {

    public PayoutNotFoundException(final long orderId, final long receiverId) {
        super("Payout with orderId: {%s} and receiverId: {%s} not found".formatted(orderId, receiverId));
    }
}
