package com.yuosef.ecommerce.email;

import com.yuosef.ecommerce.kafka.order.Product;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class EmailService {
    private static final Logger log = LoggerFactory.getLogger(EmailService.class);
    private final JavaMailSender mailSender;
    private final SpringTemplateEngine templateEngine;
    public EmailService(JavaMailSender mailSender, SpringTemplateEngine templateEngine) {
        this.mailSender = mailSender;
        this.templateEngine = templateEngine;
    }

    @Async
    public void sentPaymentSuccessEmail(
            String destinationEmail,
            String customerName,
            BigDecimal amount,
            String orderReference
    ) throws MessagingException {
        MimeMessage mimeMessage= mailSender.createMimeMessage();
        MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage,
                MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
                StandardCharsets.UTF_8.name());
        messageHelper.setFrom("jamalyuosef0@gmail.com");
        final String templateName = EmailTemplates.PAYMENT_CONFIRMAITON.getTemplate();

        Map<String,Object> vars= new HashMap<>();
        vars.put("customerName",customerName);
        vars.put("amount",amount);
        vars.put("orderReference",orderReference);

        Context context= new Context();
        context.setVariables(vars);
        messageHelper.setSubject(EmailTemplates.PAYMENT_CONFIRMAITON.getSubject());

        try {
            String htmlTemplate = templateEngine.process(templateName,context);
            messageHelper.setText(htmlTemplate,true);
            messageHelper.setTo(destinationEmail);
            mailSender.send(mimeMessage);
            log.info(String.format("INFO - Email successfully sent to %s with template %s,",destinationEmail,templateName));
        }catch (MessagingException exception){
            log.warn("Warning - cannot send email to {}",destinationEmail);
        }


    }








    @Async
    public void sentOrderConfirmationEmail(
            String destinationEmail,
            String customerName,
            BigDecimal amount,
            String orderReference,
            List<Product> products
    ) throws MessagingException {
        MimeMessage mimeMessage= mailSender.createMimeMessage();
        MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage,
                MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
                StandardCharsets.UTF_8.name());
        messageHelper.setFrom("jamalyuosef0@gmail.com");
        final String templateName = EmailTemplates.ORDER_CONFIRMAITON.getTemplate();

        Map<String,Object> vars= new HashMap<>();
        vars.put("customerName",customerName);
        vars.put("totalAmount",amount);
        vars.put("orderReference",orderReference);
        vars.put("products ",products);

        Context context= new Context();
        context.setVariables(vars);
        messageHelper.setSubject(EmailTemplates.ORDER_CONFIRMAITON.getSubject());

        try {
            String htmlTemplate = templateEngine.process(templateName,context);
            messageHelper.setText(htmlTemplate,true);
            messageHelper.setTo(destinationEmail);
            mailSender.send(mimeMessage);
            log.info(String.format("INFO - Email successfully sent to %s with template %s,",destinationEmail,templateName));
        }catch (MessagingException exception){
            log.warn("Warning - cannot send email to {}",destinationEmail);
        }


    }

}
