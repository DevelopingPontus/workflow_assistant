package com.example.chasky.features.data;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.example.chasky.features.user.User;

import tools.jackson.core.type.TypeReference;
import tools.jackson.databind.ObjectMapper;


public class DataPersistence {
    private final String filePath = "src/main/resources/user_data.json";

    ObjectMapper mapper;

    public DataPersistence() {
        mapper = new ObjectMapper();
    }

    public List<User> readUsers() {
        List<User> loadedUsers = Collections.synchronizedList(new ArrayList<>());
        try {
            String jsonContent = Files.readString(Paths.get(filePath));

            loadedUsers = mapper.readValue(jsonContent, new TypeReference<List<User>>() {
            });
        } catch (IOException e) {
            System.out.println("No previous data found. Starting fresh.");
        }
        return loadedUsers;
    }

    public void writeUsers(List<User> currentUsers) {

        try {
            String jsonOutput = mapper.writeValueAsString(currentUsers);

            Files.writeString(Paths.get(filePath), jsonOutput);

        } catch (IOException e) {
            System.out.println("Error writing users. " + e.getMessage());
        }
    }
}
