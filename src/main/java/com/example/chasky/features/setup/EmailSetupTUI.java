package com.example.chasky.features.setup;

import java.util.Scanner;
import java.util.regex.Pattern;

import com.example.chasky.common.extras.Insulter;
import com.example.chasky.features.data.DataPersistence;

public class EmailSetupTUI {
    private Scanner scanner;
    private DataPersistence dataPersistence;

    public EmailSetupTUI(DataPersistence dataPersistence) {
        this.scanner = new Scanner(System.in);
        this.dataPersistence = dataPersistence;
    }

    public void run() {
        setupLoop();
    }

    private void setupLoop() {
        System.out.println("\nWould you like to create a .env file with EMAIL and APP_PASSWORD? (y/n)");

        String pick = scanner.nextLine().trim();

        if (pick.equalsIgnoreCase("n")) {
            return;
        } else if (pick.equalsIgnoreCase("y")) {
            String email = enterEmail();
            if (email.equalsIgnoreCase("EXIT")) {
                return;
            }
            String appPassword = enterAppPassword();
            if (appPassword.equalsIgnoreCase("EXIT")) {
                return;
            }
            dataPersistence.writeEnv(email, appPassword);
        }
    }

    private String enterEmail() {
        System.out
                .println(
                        "\nEnter a @gmail you want this application to use for sending data to your user email or enter EXIT to quit setup.");
        String email = scanner.nextLine();

        if (email.equalsIgnoreCase("EXIT")) {
            return email;
        }

        // Checks if email is valid format.
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@" +
                "(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        Pattern p = Pattern.compile(emailRegex);
        if (email != null && p.matcher(email).matches()) {
        } else {
            System.out.println("Invalid email format. " + email);
            Insulter.printInsult();
            enterEmail();
        }
        return email;
    }

    private String enterAppPassword() {
        System.out.println(
                "\nEnter the app password (16-character password from your Google Account) or enter EXIT to quit setup. (XXXX XXXX XXXX XXXX)");
        String appPassword = scanner.nextLine();

        if (appPassword.equalsIgnoreCase("EXIT")) {
            return appPassword;
        }

        if (appPassword != null && appPassword.replaceAll("\\s+", "").length() == 16) {
            return appPassword;
        } else {
            System.out.println("Invalid app password format. App password must be 16 characters.");
            Insulter.printInsult();
            return enterAppPassword();
        }
    }

}
