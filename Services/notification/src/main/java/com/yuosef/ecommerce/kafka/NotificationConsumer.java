package com.yuosef.ecommerce.kafka;

import com.yuosef.ecommerce.email.EmailService;
import com.yuosef.ecommerce.kafka.order.OrderConfirmation;
import com.yuosef.ecommerce.kafka.payment.PaymentConfirmation;
import com.yuosef.ecommerce.notification.Notification;
import com.yuosef.ecommerce.notification.NotificationDao;
import com.yuosef.ecommerce.notification.NotificationType;
import jakarta.mail.MessagingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class NotificationConsumer {
    private static final Logger log = LoggerFactory.getLogger(NotificationConsumer.class);
    private final NotificationDao repo;
    private final EmailService emailService;

    public NotificationConsumer(NotificationDao repo, EmailService emailService) {
        this.repo = repo;
        this.emailService = emailService;
    }
    @KafkaListener(topics = "payment-topic")
    public void consumePaymentSuccessNotification(PaymentConfirmation paymentConfirmation) throws MessagingException {
        log.info(String.format("Consuming the message from payment-topic Topic :: %s", paymentConfirmation));
        repo.save(new Notification(
                NotificationType.PAYMENT_CONFIRMATION,
                LocalDateTime.now(),
                paymentConfirmation
        ));

      var customerName= paymentConfirmation.customerFname()+" "+ paymentConfirmation.customerLname();
      emailService.sentPaymentSuccessEmail(
              paymentConfirmation.customerEmail(),
              customerName,
              paymentConfirmation.amount(),
              paymentConfirmation.orderReference()
      );
    }
    @KafkaListener(topics = "order-topic")
    public void consumeOrderSuccessNotification(OrderConfirmation orderConfirmation) throws MessagingException {
        log.info(String.format("Consuming the message from order-topic Topic :: %s", orderConfirmation));
        repo.save(new Notification(
                NotificationType.ORDER_CONFIRMATION,
                LocalDateTime.now(),
                orderConfirmation
        ));

        var customerName= orderConfirmation.customer().firstname()+" "+ orderConfirmation.customer().lastname();
        emailService.sentOrderConfirmationEmail(
                orderConfirmation.customer().email(),
                customerName,
                orderConfirmation.amount(),
                orderConfirmation.orderReference(),
                orderConfirmation.products()
        );
    }

}
