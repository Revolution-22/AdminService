package com.revolution.admin.service.infrastructure.database;

import com.revolution.admin.service.api.dto.PayoutDto;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
class PayoutMapper {

    PayoutDto toDto(final PayoutEntity payoutEntity) {
        return new PayoutDto(payoutEntity.getBankAccountNumber(), payoutEntity.getId().getOrderId(), payoutEntity.getId().getReceiverId(), BigDecimal.valueOf(payoutEntity.getAmount()), payoutEntity.isPaid());
    }

    PayoutEntity toEntity(final PayoutDto payoutDto) {
        return new PayoutEntity(new PayoutEntityId(payoutDto.orderId(), payoutDto.receiverId()), payoutDto.bankAccountNumber(), payoutDto.amount().doubleValue(), payoutDto.isPaid());

    }
}
