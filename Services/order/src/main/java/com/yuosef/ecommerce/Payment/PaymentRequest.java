package com.yuosef.ecommerce.Payment;

import com.yuosef.ecommerce.Customer.CustomerResponse;
import com.yuosef.ecommerce.Models.PaymentMethod;

import java.math.BigDecimal;

public record PaymentRequest(
        BigDecimal amount,
        PaymentMethod paymentMethod,
        Integer orderId,
        String orderReference,
        CustomerResponse customer
) {
}
