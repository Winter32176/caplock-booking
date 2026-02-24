package com.caplock.booking.service;

import java.math.BigDecimal;

public interface PaymentGateway {
    record TransactionResult(String transactionId, boolean success, String message) {
    }

    default TransactionResult processPayment(Long bookingId, BigDecimal amount, String paymentMethod) {
        return new TransactionResult("txn_" + bookingId + "_" + System.currentTimeMillis(), true, "Payment processed successfully");
    }
}
