package com.yuosef.ecommerce.Controller;

import com.yuosef.ecommerce.Models.OrderRequest;
import com.yuosef.ecommerce.Models.OrderResponse;
import com.yuosef.ecommerce.Services.OrderService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/orders")
public class OrderController {

    private final OrderService service;


    public OrderController(OrderService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Integer> createOrder(
            @RequestBody @Valid OrderRequest orderRequest

    ){
        return  ResponseEntity.ok(service.createOrder(orderRequest));
    }
    @GetMapping
    public ResponseEntity<List<OrderResponse>> findall(){
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{order-id}")
    public ResponseEntity<OrderResponse> findById(
            @PathVariable("order-id") Integer orderId
    ){
        return ResponseEntity.ok(service.findByID(orderId));
    }

}
