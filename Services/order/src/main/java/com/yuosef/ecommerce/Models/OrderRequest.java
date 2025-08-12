package com.yuosef.ecommerce.Models;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;
import java.util.List;

public record OrderRequest(
        Integer id,
        String reference,
        @Positive(message = "orders amount should be positive")
        BigDecimal amount,
        @NotNull(message = "payment not null pls")
        PaymentMethod  paymentMethod,
        @NotNull(message = "customer should be present")
        @NotEmpty(message = "customer should be present")
        @NotBlank(message = "customer should be present")
        String customerId,
        @NotEmpty(message = "must add at least one product")
        List<PurchaseRequest> products
) {
}
