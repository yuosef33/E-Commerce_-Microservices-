package com.yuosef.ecommerce.email;

public enum EmailTemplates {
    PAYMENT_CONFIRMAITON("payment-confirmation.html","Payment successfully processed"),
    ORDER_CONFIRMAITON("order-confirmation.html","order confirmation ");

    private final String template;
    private final String subject;

    EmailTemplates(String template, String subject) {
        this.template = template;
        this.subject = subject;
    }

    public String getTemplate() {
        return template;
    }

    public String getSubject() {
        return subject;
    }
}
