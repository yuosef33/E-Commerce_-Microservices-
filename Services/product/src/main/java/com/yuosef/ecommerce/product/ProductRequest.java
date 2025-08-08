package com.yuosef.ecommerce.product;

import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record ProductRequest(
         Integer id,
         @NotNull(message = "product name required ")
         String name,
         @NotNull(message = "product desc required ")
         String description,
         @NotNull(message = "product quantity required ")
         Double quantity,
         @NotNull(message = "product price required ")
         BigDecimal price,
         @NotNull(message = "product category required ")
         Integer categoryId
) {
}
