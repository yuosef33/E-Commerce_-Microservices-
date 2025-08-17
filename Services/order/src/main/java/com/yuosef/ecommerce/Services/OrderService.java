package com.yuosef.ecommerce.Services;

import com.yuosef.ecommerce.Kafka.OrderConfirmation;
import com.yuosef.ecommerce.Kafka.OrderProducer;
import com.yuosef.ecommerce.Mapper.OrderMapper;
import com.yuosef.ecommerce.Customer.CustomerClient;
import com.yuosef.ecommerce.Daos.OrderDao;
import com.yuosef.ecommerce.Models.OrderRequest;
import com.yuosef.ecommerce.Models.OrderResponse;
import com.yuosef.ecommerce.Models.PurchaseRequest;
import com.yuosef.ecommerce.Payment.PaymentClient;
import com.yuosef.ecommerce.Payment.PaymentRequest;
import com.yuosef.ecommerce.Product.ProductClient;
import com.yuosef.ecommerce.orderline.OrderLineRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderService {

        private final OrderDao repo;
        private final CustomerClient customerClientclient;
        private final OrderMapper mapper;
        private final ProductClient productClient;
        private final OrderLineService orderLineService;
        private final OrderProducer orderProducer;
        private final PaymentClient paymentClient;
    public OrderService(OrderDao repo, CustomerClient customerClientclient, OrderMapper mapper, ProductClient productClient, OrderLineService orderLineService, OrderProducer orderProducer, PaymentClient paymentClient) {
        this.repo = repo;
        this.customerClientclient = customerClientclient;
        this.mapper = mapper;
        this.productClient = productClient;
        this.orderLineService = orderLineService;
        this.orderProducer = orderProducer;
        this.paymentClient = paymentClient;
    }

    public Integer createOrder(OrderRequest orderRequest) {

        var customer = this.customerClientclient.findCustomerById(orderRequest.customerId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "no customer exist with this id"));

        var purchaseProducts = this.productClient.purchaseProducts(orderRequest.products());

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
            var paymentRequest=new PaymentRequest(
              orderRequest.amount(),
              orderRequest.paymentMethod(),
              order.getId(),
              order.getReference(),
              customer
            );
       paymentClient.requestOrderPayment(paymentRequest);
            orderProducer.sendOrderConfirmation(
                    new OrderConfirmation(
                            orderRequest.reference(),
                            orderRequest.amount(),
                            orderRequest.paymentMethod(),
                            customer,
                            purchaseProducts

                    )
            );
            return order.getId();



    }

    public List<OrderResponse> findAll() {
        return repo.findAll().stream()
                .map(mapper::fromOrder)
                .collect(Collectors.toList());

    }

    public OrderResponse findByID(Integer orderId) {
    return repo.findById(orderId).map(mapper::fromOrder)
            .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,"Order not found"));
    }

}
