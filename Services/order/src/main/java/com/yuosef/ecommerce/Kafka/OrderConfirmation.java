package com.yuosef.ecommerce.Kafka;

import com.yuosef.ecommerce.Customer.CustomerResponse;
import com.yuosef.ecommerce.Models.PaymentMethod;
import com.yuosef.ecommerce.Product.PurchaseResponse;

import java.math.BigDecimal;
import java.util.List;

public record OrderConfirmation(
        String orderRefernce,
        BigDecimal totalAmount,
        PaymentMethod paymentMethod,
        CustomerResponse customer,
        List<PurchaseResponse> products


) {
}
