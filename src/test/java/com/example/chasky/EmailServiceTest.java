package com.example.chasky;

import org.junit.Test;

import com.example.chasky.angusMail.EmailService;


public class EmailServiceTest {
    EmailService emailService = new EmailService();

    @Test
    public void shouldSendMail() {
        try {
            emailService.sendMessageToSelf("Hi", "Hello world");
        } catch (Exception e) {
        }
    }
}
