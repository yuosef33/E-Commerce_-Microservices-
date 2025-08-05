package com.yuosef.ecommerce.Services;

import com.yuosef.ecommerce.Models.Customer;
import com.yuosef.ecommerce.Models.CustomerRespnse;
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
