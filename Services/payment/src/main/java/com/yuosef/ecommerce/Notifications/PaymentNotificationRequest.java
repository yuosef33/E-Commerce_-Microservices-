package com.yuosef.ecommerce.Notifications;

import com.yuosef.ecommerce.Model.PaymentMethod;

import java.math.BigDecimal;

public record PaymentNotificationRequest(
        String orderReference,
        BigDecimal amount,
        PaymentMethod paymentMethod,
        String customerFname,
        String customerLname,
        String customerEmail
) {
}
