package com.yuosef.ecommerce.Services;

import com.yuosef.ecommerce.Daos.OrderLineDao;
import com.yuosef.ecommerce.Mapper.OrderLineMapper;
import com.yuosef.ecommerce.orderline.OrderLineRequest;
import org.springframework.stereotype.Service;

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
}
