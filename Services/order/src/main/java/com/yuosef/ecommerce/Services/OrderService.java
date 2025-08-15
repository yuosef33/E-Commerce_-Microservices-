package com.yuosef.ecommerce.Services;

import com.yuosef.ecommerce.Mapper.OrderMapper;
import com.yuosef.ecommerce.Customer.CustomerClient;
import com.yuosef.ecommerce.Daos.OrderDao;
import com.yuosef.ecommerce.Models.OrderRequest;
import com.yuosef.ecommerce.Models.PurchaseRequest;
import com.yuosef.ecommerce.Product.ProductClient;
import com.yuosef.ecommerce.orderline.OrderLineRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class OrderService {

        private final OrderDao repo;
        private final CustomerClient customerClientclient;
        private final OrderMapper mapper;
        private final ProductClient productClient;
        private final OrderLineService orderLineService;
    public OrderService(OrderDao repo, CustomerClient customerClientclient, OrderMapper mapper, ProductClient productClient, OrderLineService orderLineService) {
        this.repo = repo;
        this.customerClientclient = customerClientclient;
        this.mapper = mapper;
        this.productClient = productClient;
        this.orderLineService = orderLineService;
    }

    public Integer createOrder(OrderRequest orderRequest) {

        var customer = this.customerClientclient.findCustomerById(orderRequest.customerId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "no customer exist with this id"));

        this.productClient.purchaseProducts(orderRequest.products());

        var order = this.repo.save(mapper.toOrder(orderRequest));

        for (PurchaseRequest purchaseRequest : orderRequest.products()){
            orderLineService.saveOrderLine(
                    new OrderLineRequest(
                            null,
                            order.getId(),
                            purchaseRequest.productId(),
                            purchaseRequest.quantity()
                    )
            );
         }

            // todo start payment process

            return null;



    }
}
