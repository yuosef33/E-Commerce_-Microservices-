package com.yuosef.ecommerce.Customer;

public record CustomerResponse(
        String id,
        String firstName,
        String lastName,
        String email
) {
}
