package com.yuosef.ecommerce.Notifications;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
public class NotificationProducer {
    private static final Logger log = LoggerFactory.getLogger(NotificationProducer.class);
    private final KafkaTemplate<String ,PaymentNotificationRequest> KafkaTemplate;

    public NotificationProducer(org.springframework.kafka.core.KafkaTemplate<String, PaymentNotificationRequest> kafkaTemplate) {
        KafkaTemplate = kafkaTemplate;
    }


    public void sendNotification(PaymentNotificationRequest request){
        log.info("Sending notification with body <{}>",request);
        Message<PaymentNotificationRequest> message = MessageBuilder
                .withPayload(request)
                .setHeader(KafkaHeaders.TOPIC,"PaymentTopic")
                .build();
        KafkaTemplate.send(message);
    }
}
