package com.yuosef.ecommerce.Model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import org.springframework.validation.annotation.Validated;

@Validated
public record Customer(
        String id,
        @NotNull(message = "name required")
        String firstname,
        @NotNull(message = "name required")
        String lastname,
        @NotNull(message = "email required")
        @Email(message = "Email format pls")
        String email
) {
}
