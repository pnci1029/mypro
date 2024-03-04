package com.example.practicaltest.spring.spring.client.mail;

import org.springframework.stereotype.Component;

@Component
public class MailSendClient {
    public boolean sendEmail(String fromEmail, String toEmail, String subject, String content) {


        return true;
    }
}
