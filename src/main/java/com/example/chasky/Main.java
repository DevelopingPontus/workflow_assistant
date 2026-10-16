package com.example.chasky;

import com.example.chasky.features.app.App;
import com.example.chasky.features.app.AppFactory;

import jakarta.mail.MessagingException;

public class Main {
    public static void main(String[] args) throws MessagingException {
        AppFactory appFactory = new AppFactory();
        App app = appFactory.create();
        app.run();
        appFactory.shutDown();
    }
}
