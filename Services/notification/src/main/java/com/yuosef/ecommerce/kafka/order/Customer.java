package com.yuosef.ecommerce.kafka.order;

public record Customer(
        String firstname,
        String lastname,
        String email
) {
}
