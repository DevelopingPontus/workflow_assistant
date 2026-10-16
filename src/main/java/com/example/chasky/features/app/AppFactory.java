package com.example.chasky.features.app;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.example.chasky.features.angusMail.EmailService;
import com.example.chasky.features.data.DataPersistence;
import com.example.chasky.features.tui.TUI;
import com.example.chasky.features.user.User;
import com.example.chasky.mock.MockData;



public class AppFactory {
    DataPersistence dataPersistence;
    List<User> users;

    public AppFactory() {
        this.dataPersistence = new DataPersistence();
    }

    public App createWithMockData() {
        users = Collections.synchronizedList(new ArrayList<>());
        MockData mock = new MockData();
        users = mock.mockData(users);
        dataPersistence.writeUsers(users);

        EmailService emailService = new EmailService();

        TUI tui = new TUI(users, emailService);
        return new App(tui);
    }

    public App create() {
        users = Collections.synchronizedList(new ArrayList<>());
        users = dataPersistence.readUsers();

        EmailService emailService = new EmailService();

        TUI tui = new TUI(users, emailService);
        return new App(tui);
    }

    public void shutDown() {
        dataPersistence.writeUsers(users);
    }
}
