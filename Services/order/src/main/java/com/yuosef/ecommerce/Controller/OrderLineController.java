package com.yuosef.ecommerce.Controller;

import com.yuosef.ecommerce.Services.OrderLineService;
import com.yuosef.ecommerce.orderline.OrderLineResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/order-lines")
public class OrderLineController {

        private final OrderLineService service;


    public OrderLineController(OrderLineService service) {
        this.service = service;
    }

    @GetMapping("/order/{order-di}")
    public ResponseEntity<List<OrderLineResponse>> findByOrderId(
            @PathVariable("order-id") Integer orderId
    ){
        return ResponseEntity.ok(service.findAllByOrderId(orderId));
    }


}

