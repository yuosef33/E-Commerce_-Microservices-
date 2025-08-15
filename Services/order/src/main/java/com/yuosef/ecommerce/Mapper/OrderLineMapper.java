package com.yuosef.ecommerce.Mapper;

import com.yuosef.ecommerce.Models.Order;
import com.yuosef.ecommerce.Models.OrderLine;
import com.yuosef.ecommerce.orderline.OrderLineRequest;
import com.yuosef.ecommerce.orderline.OrderLineResponse;
import org.springframework.stereotype.Service;

@Service
public class OrderLineMapper {
    public OrderLine toOrderLine(OrderLineRequest orderLineRequest) {
    return new OrderLine(
            orderLineRequest.id(),
           new Order(orderLineRequest.orderId()),
            orderLineRequest.productId(),
            orderLineRequest.quantity()
    );
    }

    public OrderLineResponse toOrderLineResponse(OrderLine orderLine) {
        return new OrderLineResponse(orderLine.getId(),orderLine.getQuantity());
    }
}
