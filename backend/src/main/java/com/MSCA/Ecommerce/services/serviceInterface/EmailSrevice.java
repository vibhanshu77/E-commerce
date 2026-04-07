package com.MSCA.Ecommerce.services.serviceInterface;

public interface EmailSrevice {

    void sendEmail(String to, String subject, String body);

    void sendEmailWithHtml();

    void sendEmailWithAttachment();

}
