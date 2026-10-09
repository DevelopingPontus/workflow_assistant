package com.example.chasky;

import jakarta.mail.MessagingException;

public class Main {
    public static void main(String[] args) throws MessagingException {
        // App app = new App();
        // app.run();

        AppLLM appLLM = new AppLLM();
        appLLM.run();
    }

}
