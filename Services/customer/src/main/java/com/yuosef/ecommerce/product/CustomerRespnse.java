package com.yuosef.ecommerce.product;

public record CustomerRespnse(
        String id,
        String firstName,
        String lastName,
        String email,
        Address address
) {
}
