package com.example.chasky.features.angusMail;

import java.util.List;

import com.example.chasky.common.interfaces.iPriority;
import com.example.chasky.features.user.User;

import io.github.cdimascio.dotenv.Dotenv;
import jakarta.mail.MessagingException;

public class EmailService {

    Dotenv env = Dotenv.load();

    public EmailSender sender;
    public EmailMonitor monitor;

    final String smtpHost = "smtp.gmail.com";
    final int port = 587;
    final String email = env.get("EMAIL");
    final String password = env.get("APP_PASSWORD");
    final String mbox = "inbox";
    final int freq = 4;

    public EmailService() {
        sender = new EmailSender(smtpHost, port, email, password);
        // monitor = new EmailMonitor(smtpHost, email, password, mbox, freq);
        // monitor.monitor();
    }

    public void sendEmail(String subject, String body, String toEmail) {
        try {
            sender.send(email, toEmail, subject, body);
            System.out.println("Message was sent to " + toEmail);
        } catch (MessagingException e) {
            System.out.println("Error: EmailService.sendEmail failed");
        }
    }

    public void sendEmailToSelf(String subject, String body) {
        try {
            sender.send(email, email, subject, body);
        } catch (MessagingException e) {
            System.out.println("Error: EmailService.sendMessageToSelf failed");
        }
    }

    public void sendEmailReport(List<iPriority> topics, User user) {
        String body = "";
        for (int i = 0; i < topics.size(); i++) {
            iPriority priority = topics.get(i);
            String topic = priority.getWhat();

            body = body + (String.format("%-80s", topic) + "Priority score:" + priority.getPriorityScore() + "\n");

        }
        sendEmail("Report", body, user.getEmail());
    }

}
