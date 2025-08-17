package com.yuosef.ecommerce.kafka.payment;

import java.math.BigDecimal;

public record PaymentConfirmation(
        String orderReference,
        BigDecimal amount,
        PaymentMethod paymentMethod,
        String customerFname,
        String customerLname,
        String customerEmail
) {
}
