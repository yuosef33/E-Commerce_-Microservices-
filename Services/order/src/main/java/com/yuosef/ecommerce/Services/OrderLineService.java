package com.yuosef.ecommerce.Services;

import com.yuosef.ecommerce.Daos.OrderLineDao;
import com.yuosef.ecommerce.Mapper.OrderLineMapper;
import com.yuosef.ecommerce.orderline.OrderLineRequest;
import com.yuosef.ecommerce.orderline.OrderLineResponse;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderLineService {
    private final OrderLineDao repo;
    private final OrderLineMapper mappper;

    public OrderLineService(OrderLineDao repo, OrderLineMapper mappper) {
        this.repo = repo;
        this.mappper = mappper;
    }

    public Integer saveOrderLine(OrderLineRequest orderLineRequest) {
        var order=mappper.toOrderLine(orderLineRequest);
        return repo.save(order).getId();
    }

    public List<OrderLineResponse> findAllByOrderId(Integer orderId) {

        return repo.findAllByOrderId(orderId)
                .stream()
                .map(mappper::toOrderLineResponse)
                .collect(Collectors.toList());
    }
}
