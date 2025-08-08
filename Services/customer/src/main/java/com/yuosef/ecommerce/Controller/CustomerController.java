package com.yuosef.ecommerce.Controller;

import com.yuosef.ecommerce.product.CustomerRequest;
import com.yuosef.ecommerce.product.CustomerRespnse;
import com.yuosef.ecommerce.Services.CustomerService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/customer")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping("/")
    public ResponseEntity<String> createCustomer(
            @RequestBody @Valid CustomerRequest customerRequest
    ){
return ResponseEntity.ok(customerService.createCustomer(customerRequest));
    }

    @PutMapping("/")
    public ResponseEntity<Void> updateCustomer(
            @RequestBody @Valid CustomerRequest request
    )
    {
        customerService.updateCustomer(request);
        return ResponseEntity.accepted().build();
    }
    @GetMapping("/")
    public ResponseEntity<List<CustomerRespnse>> findAll(){
        return ResponseEntity.ok(customerService.findAll());
    }

    @GetMapping("/exist/{customer-id}")
    public ResponseEntity<CustomerRespnse> getById(
            @PathVariable("customer-id") String customerId
    ){
        return ResponseEntity.status(HttpStatus.FOUND).body(customerService.findById(customerId));
    }
    @DeleteMapping("/{customer-id}")
    public ResponseEntity<Void> delteCustomer(
            @PathVariable("customer-id") String customerId
    ){
        customerService.delteCustomer(customerId);
        return ResponseEntity.accepted().build();

    }



}
