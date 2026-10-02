package com.example.chasky.angusMail;

import io.github.cdimascio.dotenv.Dotenv;
import jakarta.mail.MessagingException;

public class EmailService {

    Dotenv env = Dotenv.load();

    public EmailSender sender;
    public EmailMonitor monitor;

    final String smtpHost = "smtp.gmail.com";
    final int port = 587;
    final String username = env.get("USER_NAME");
    final String password = env.get("PASSWORD");
    final String mbox = "inbox";
    final int freq = 4;

    public EmailService() {
        sender = new EmailSender(smtpHost, port, username, password);
        monitor = new EmailMonitor(smtpHost, username, password, mbox, freq);
        monitor.monitor();
    }

    public void sendMessageToSelf(String subject, String body) {
        try {
            sender.send(username, username, subject, body);
        } catch (MessagingException e) {
            System.out.println("Error: EmailService.SendMessageToSelf failed");
        }
    }

}
