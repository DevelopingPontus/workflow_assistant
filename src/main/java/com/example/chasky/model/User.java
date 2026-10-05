package com.example.chasky.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Topic> getTopics() {
        return topics;
    }

    public void addTopic(Topic topic) {
        topics.add(topic);
    }

    /**
     * Get topics with a deadline and sort them by priority score.
     * 
     * @return
     */
    public List<iPriority> getTopicsByPrioriy() {
        List<iPriority> priorities = new ArrayList<>();
        for (Topic topic : topics) {
            if (topic instanceof iPriority) {
                priorities.add((iPriority) topic);
            }
        }
        Collections.sort(priorities, (a, b) -> Float.compare(a.getPriorityScore(), b.getPriorityScore()));
        return priorities;
    }




}
