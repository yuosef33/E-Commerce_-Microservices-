package com.yuosef.ecommerce.Service;

import com.yuosef.ecommerce.Model.PaymentRequest;
import com.yuosef.ecommerce.Daos.PaymentDao;
import com.yuosef.ecommerce.Notifications.NotificationProducer;
import com.yuosef.ecommerce.Notifications.PaymentNotificationRequest;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {

    private final PaymentDao repo;
    private final PaymentMapper mapper;
    private final NotificationProducer notificationProducer;
    public PaymentService(PaymentDao repo, PaymentMapper mapper, NotificationProducer notificationProducer) {
        this.repo = repo;
        this.mapper = mapper;
        this.notificationProducer = notificationProducer;
    }

    public Integer createPayemnt(PaymentRequest request) {
        var payment = repo.save(mapper.toPayment(request));
        notificationProducer.sendNotification(
                new PaymentNotificationRequest(
                        request.orderReference(),
                        request.amount(),
                        request.paymentMethod(),
                        request.customer().firstname(),
                        request.customer().lastname(),
                        request.customer().email()
                )
        );
        return payment.getId();
    }
}
