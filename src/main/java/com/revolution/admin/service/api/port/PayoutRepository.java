package com.revolution.admin.service.api.port;

import com.revolution.admin.service.api.dto.PayoutDto;
import com.revolution.admin.service.api.query.PayoutFilterQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface PayoutRepository {
    Page<PayoutDto> findAll(Pageable pageable, PayoutFilterQuery filterQuery);

    PayoutDto save(PayoutDto payoutDto);

    Optional<PayoutDto> findByOrderIdAndReceiverId(long orderId, long receiverId);
}
