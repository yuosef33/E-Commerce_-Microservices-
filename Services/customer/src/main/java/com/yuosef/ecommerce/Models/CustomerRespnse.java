package com.yuosef.ecommerce.Models;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

public record CustomerRespnse(
        String id,
        String firstName,
        String lastName,
        String email,
        Address address
) {
}
