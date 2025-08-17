package com.yuosef.ecommerce.Controller;

import com.yuosef.ecommerce.Model.PaymentRequest;
import com.yuosef.ecommerce.Service.PaymentService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/payments")
public class PaymentController {

    private final PaymentService service;

    public PaymentController(PaymentService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Integer> createPayemtn(
            @RequestBody @Valid PaymentRequest request
    ){
        return ResponseEntity.ok(service.createPayemnt(request));
    }
}
