package com.revolution.admin.service.infrastructure.database;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

interface PayoutJpaRepository extends JpaRepository<PayoutEntity, PayoutEntityId> {

    @Query(value = """
        SELECT p.*
        FROM payouts p
        JOIN users u ON p.receiver_id = u.id
        WHERE (:username IS NULL OR u.username ILIKE CONCAT('%', :username, '%'))
          AND (:paid IS NULL OR p.paid = :paid)
        ORDER BY p.order_id, p.receiver_id
        LIMIT :limit OFFSET :offset
    """, nativeQuery = true)
    Page<PayoutEntity> findAllByFilterQuery(Pageable pageable, String username, Boolean paid);

    @Query("SELECT p FROM PayoutEntity p WHERE p.id.orderId = :orderId AND p.id.receiverId = :receiverId")
    Optional<PayoutEntity> findByOrderIdAndReceiverId(@Param("orderId") long orderId, @Param("receiverId") long receiverId);

}
