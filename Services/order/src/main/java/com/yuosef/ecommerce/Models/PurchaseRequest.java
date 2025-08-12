package com.yuosef.ecommerce.Models;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record PurchaseRequest(
    @NotNull(message = "input product")
    Integer productId,
    @Positive(message = "must be positive")
    double quantity
) {
}
