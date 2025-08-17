package com.yuosef.ecommerce.notification;

import com.yuosef.ecommerce.kafka.order.OrderConfirmation;
import com.yuosef.ecommerce.kafka.payment.PaymentConfirmation;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document
public class Notification {
    @Id
    private String id;
    private NotificationType type;

    private LocalDateTime notificationDate;
    private OrderConfirmation orderConfirmation;
    private PaymentConfirmation paymentConfirmation;

    public Notification() {
    }

    public Notification(String id, NotificationType type, LocalDateTime notificationDate, OrderConfirmation orderConfirmation, PaymentConfirmation paymentConfirmation) {
        this.id = id;
        this.type = type;
        this.notificationDate = notificationDate;
        this.orderConfirmation = orderConfirmation;
        this.paymentConfirmation = paymentConfirmation;
    }

    public Notification(NotificationType type, LocalDateTime notificationDate, OrderConfirmation orderConfirmation) {
        this.type = type;
        this.notificationDate = notificationDate;
        this.orderConfirmation = orderConfirmation;
    }

    public Notification(NotificationType type, LocalDateTime notificationDate, PaymentConfirmation paymentConfirmation) {
        this.type = type;
        this.notificationDate = notificationDate;
        this.paymentConfirmation = paymentConfirmation;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public NotificationType getType() {
        return type;
    }

    public void setType(NotificationType type) {
        this.type = type;
    }

    public LocalDateTime getNotificationDate() {
        return notificationDate;
    }

    public void setNotificationDate(LocalDateTime notificationDate) {
        this.notificationDate = notificationDate;
    }

    public OrderConfirmation getOrderConfirmation() {
        return orderConfirmation;
    }

    public void setOrderConfirmation(OrderConfirmation orderConfirmation) {
        this.orderConfirmation = orderConfirmation;
    }

    public PaymentConfirmation getPaymentConfirmation() {
        return paymentConfirmation;
    }

    public void setPaymentConfirmation(PaymentConfirmation paymentConfirmation) {
        this.paymentConfirmation = paymentConfirmation;
    }
}
