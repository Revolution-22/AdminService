package com.revolution.admin.service.api.exception;

public class PayoutNotFoundException extends RuntimeException {

    public PayoutNotFoundException(long orderId, long receiverId) {
        super("Payout with orderId: {%s} and receiverId: {%s} not found".formatted(orderId, receiverId));
    }
}
