package com.example.chasky.features.app;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import com.example.chasky.features.angusMail.EmailService;
import com.example.chasky.features.data.DataPersistence;
import com.example.chasky.features.setup.EmailSetupTUI;
import com.example.chasky.features.topic.TopicService;
import com.example.chasky.features.tui.AppTUI;
import com.example.chasky.features.user.User;
import com.example.chasky.features.user.UserService;
import com.example.chasky.mock.MockData;

public class AppFactory {
    private DataPersistence dataPersistence;
    private List<User> users;

    String mockDataFile = "src/main/resources/mock_user_data.json";
    String dataFile = "src/main/resources/user_data.json";

    public AppFactory() {
    }

    public App createWithMockData() {
        ensureGitignore();
        dataPersistence = new DataPersistence(mockDataFile);
        MockData mock = new MockData();
        users = mock.mockData(users);

        UserService userService = new UserService(users);
        TopicService topicService = new TopicService();

        EmailService emailService = setupEmailService();

        AppTUI appTui = new AppTUI(userService, topicService, emailService);
        return new App(appTui);
    }

    public App create() {
        ensureGitignore();
        dataPersistence = new DataPersistence(dataFile);
        users = dataPersistence.readUsers();

        UserService userService = new UserService(users);
        TopicService topicService = new TopicService();

        EmailService emailService = setupEmailService();

        AppTUI appTui = new AppTUI(userService, topicService, emailService);
        return new App(appTui);
    }

    public void shutDown() {
        dataPersistence.writeUsers(users);
    }

    public EmailService setupEmailService() {
        EmailService emailService;
        try {
            emailService = new EmailService();
        } catch (Exception e) {
            System.out.println("");
            System.out.println("Warning: ");
            System.out.println(
                    "No .env file with EMAIL and APP_PASSWORD was found. Email service will not work without credentials for email of type @gmail this app to use.");
            EmailSetupTUI emailSetupTUI = new EmailSetupTUI(dataPersistence);
            emailSetupTUI.run();
            emailService = new EmailService();
        }
        return emailService;
    }
    
    public void ensureGitignore() {
    String gitignorePath = ".gitignore";
    String[] requiredEntries = {"models/", "target/", "", ".env", "user_data.json"};
    String requiredContent = "models/\ntarget/\n\n.env\nuser_data.json";
    
    try {
        Path path = Paths.get(gitignorePath);
        
        if (!Files.exists(path)) {
            // Create new .gitignore
            Files.writeString(path, requiredContent);
            System.out.println(".gitignore created successfully.");
        } else {
            // Check if required entries exist
            String currentContent = Files.readString(path);
            boolean needsUpdate = false;
            
            for (String entry : requiredEntries) {
                if (!entry.isEmpty() && !currentContent.contains(entry)) {
                    needsUpdate = true;
                    break;
                }
            }
            
            if (needsUpdate) {
                // Append missing entries
                String updated = currentContent;
                if (!currentContent.endsWith("\n")) {
                    updated += "\n";
                }
                updated += requiredContent;
                Files.writeString(path, updated);
                System.out.println(".gitignore updated with missing entries.");
            } else {
                System.out.println(".gitignore already exists with all required entries.");
            }
        }
    } catch (Exception e) {
        System.out.println("Error managing .gitignore: " + e.getMessage());
    }
}


}
