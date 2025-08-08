package com.yuosef.ecommerce.product;

import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record ProductResponse(
        Integer id,
        String name,
        String description,
        Double quantity,
        BigDecimal price,
        Integer categoeryId,
        String categoryName,
        String categoryDesecription
) {
}
