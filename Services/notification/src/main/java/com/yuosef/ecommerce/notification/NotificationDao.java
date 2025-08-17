package com.yuosef.ecommerce.notification;

import com.yuosef.ecommerce.kafka.payment.PaymentConfirmation;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface NotificationDao extends MongoRepository<Notification,String> {
}
