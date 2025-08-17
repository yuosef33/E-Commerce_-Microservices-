package com.yuosef.ecommerce.Service;

import com.yuosef.ecommerce.Model.PaymentRequest;
import com.yuosef.ecommerce.Model.Payment;
import org.springframework.stereotype.Service;

@Service
public class PaymentMapper {

    public Payment toPayment(PaymentRequest request) {
        return new Payment(
                request.id(),
                request.amount(),
                request.paymentMethod(),
                request.orderId()
        );
    }
}
