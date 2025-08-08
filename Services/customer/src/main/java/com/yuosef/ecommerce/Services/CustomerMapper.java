package com.yuosef.ecommerce.Services;

import com.yuosef.ecommerce.product.Customer;
import com.yuosef.ecommerce.product.CustomerRespnse;
import org.springframework.stereotype.Service;

@Service
public class CustomerMapper {
    public CustomerRespnse toResponse(Customer customer) {

        return new CustomerRespnse(
                customer.getId(),
                customer.getFirstName(),
                customer.getLastName(),
                customer.getEmail(),
                customer.getAddress()
        );
    }
}
