package com.revolution.admin.service.infrastructure.database;

import com.revolution.admin.service.api.dto.PayoutDto;
import com.revolution.admin.service.api.port.PayoutRepository;
import com.revolution.admin.service.api.query.PayoutFilterQuery;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
class PayoutRepositoryAdapter implements PayoutRepository {

    private final PayoutJpaRepository payoutJpaRepository;
    private final PayoutMapper payoutMapper;

    @Override
    public Page<PayoutDto> findAllByFilterQuery(final Pageable pageable, final PayoutFilterQuery filterQuery) {
        return payoutJpaRepository.findAllByFilterQuery(pageable, filterQuery.username(), filterQuery.isPaid())
                .map(payoutMapper::toDto);
    }

    @Override
    public PayoutDto save(final PayoutDto payoutDto) {
        return payoutMapper.toDto(
                payoutJpaRepository.save(
                        payoutMapper.toEntity(payoutDto)
                )
        );
    }

    @Override
    public Optional<PayoutDto> findByOrderIdAndReceiverId(final long orderId, final long receiverId) {
        return payoutJpaRepository.findByOrderIdAndReceiverId(orderId, receiverId)
                .map(payoutMapper::toDto);
    }
}
