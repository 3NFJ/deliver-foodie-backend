package com.nfjs.fooddelivery.payment.entity;

import com.nfjs.fooddelivery.order.entity.Order;
import com.nfjs.fooddelivery.payment.enums.CardCompany;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "p_payments")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "payment_id")
    private UUID paymentId;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;

    @Column(name = "card_company", nullable = false)
    @Enumerated(EnumType.STRING)
    private CardCompany cardCompany;

    @Column(name = "installment_months")
    private Integer installmentMonths;

    @Column(name = "payment_number", nullable = false)
    private String paymentNumber;

    @Column(name = "total_amount", nullable = false)
    private Integer totalAmount;

    @Column(name = "card_number", nullable = false)
    private String cardNumber;

    @Column(name = "is_installment", nullable = false)
    private Boolean isInstallment;

    @Column(name = "request_at", nullable = false)
    private LocalDateTime requestAt;

    @Column(name = "approved_at")
    private LocalDateTime approvedAt;

    @Column(name = "cancelled_at")
    private LocalDateTime cancelledAt;

    @Builder
    public Payment(Order order, CardCompany cardCompany, String cardNumber, Integer installmentMonths, String paymentNumber, Boolean isInstallment) {
        this.order = order;
        this.cardCompany = cardCompany;
        this.cardNumber = cardNumber;
        this.installmentMonths = installmentMonths;
        this.paymentNumber = paymentNumber;
        this.isInstallment = isInstallment;
        this.totalAmount = order.getTotalAmount();
        this.requestAt = LocalDateTime.now();
    }
}