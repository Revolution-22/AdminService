package com.revolution.admin.service.domain.adapter

import com.revolution.admin.service.api.dto.PayoutDto
import com.revolution.admin.service.api.port.PayoutRepository
import com.revolution.admin.service.api.port.UserRespository
import com.revolution.admin.service.api.query.PayoutFilterQuery
import com.revolution.admin.service.api.query.UserFilterQuery
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable

class TestPayoutRepository implements PayoutRepository {

    private final UserRespository userRespository

    TestPayoutRepository(final UserRespository userRespository) {
        this.userRespository = userRespository
    }

    protected Map<Key, PayoutDto> database = new HashMap<>();

    @Override
    Page<PayoutDto> findAll(final Pageable pageable, final PayoutFilterQuery filterQuery) {
        List<PayoutDto> filtered = database.values().stream()
                .filter(payout -> filterQuery.username() == null || userRespository.findAll(
                        PageRequest.of(0, 10000),
                        new UserFilterQuery(filterQuery.username(), null)
                ).size > 0)
                .filter(payout -> filterQuery.isPaid() == null || payout.isPaid().equals(filterQuery.isPaid()))
                .toList()

        int start = (int) pageable.getOffset();
        int end = Math.min(start + pageable.getPageSize(), filtered.size())
        List<PayoutDto> paginated = start <= end ? filtered.subList(start, end) : List.of()

        new PageImpl<>(paginated, pageable, filtered.size())
    }

    @Override
    PayoutDto save(final PayoutDto payoutDto) {
        Key key = new Key(payoutDto.orderId(), payoutDto.receiverId())
        database.put(key, payoutDto)
        payoutDto
    }

    @Override
    Optional<PayoutDto> findByOrderIdAndReceiverId(final long orderId, final long receiverId) {
        Optional.ofNullable(database.get(new Key(orderId, receiverId)))
    }

    static class Key {
        private Long orderId
        private Long receiverId

        Key(final Long orderId, final Long receiverId) {
            this.orderId = orderId
            this.receiverId = receiverId
        }

        boolean equals(final o) {
            if (this.is(o)) return true
            if (o == null || getClass() != o.class) return false

            final Key key = (Key) o

            if (orderId != key.orderId) return false
            if (receiverId != key.receiverId) return false

            return true
        }

        int hashCode() {
            int result
            result = (orderId != null ? orderId.hashCode() : 0)
            result = 31 * result + (receiverId != null ? receiverId.hashCode() : 0)
            return result
        }
    }
}
