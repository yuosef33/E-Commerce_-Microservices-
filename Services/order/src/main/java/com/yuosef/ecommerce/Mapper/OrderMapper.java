package com.yuosef.ecommerce.Mapper;

import com.yuosef.ecommerce.Models.Order;
import com.yuosef.ecommerce.Models.OrderRequest;
import org.springframework.stereotype.Service;

@Service
public class OrderMapper {
    public Order toOrder(OrderRequest orderRequest) {
          return new Order(
                  orderRequest.id(),
                  orderRequest.reference(),
                  orderRequest.amount(),
                  orderRequest.paymentMethod(),
                  orderRequest.customerId()
          );
    }
}
