package com.revolution.admin.service.infrastructure.database;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Table(name = "payouts")
@Builder
@Getter
@Entity
public class PayoutEntity {

    @EmbeddedId
    private PayoutEntityId id;

    private String bankAccountNumber;

    private double amount;

    @Column(name = "paid")
    private boolean isPaid;
}
