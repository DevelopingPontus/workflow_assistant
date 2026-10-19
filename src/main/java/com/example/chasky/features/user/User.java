package com.example.chasky.features.user;

import java.util.ArrayList;
import java.util.List;

import com.example.chasky.features.topic.Topic;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
@NoArgsConstructor 
public class User {
    private String email;

    /**
     * List of polymorphic topics
     */
    private List<Topic> topics;

    public User(String email) {
        this.email = email;
        this.topics = new ArrayList<>();
    }

    public void addTopic(Topic topic) {
        topics.add(topic);
    }

}
