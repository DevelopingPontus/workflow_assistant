package com.example.chasky.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
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

    /**
     * Get topics with a deadline and sort them by priority score.
     * 
     * @return
     */
    public List<iPriority> getTopicsByPrioriy() {
        List<iPriority> priorities = new ArrayList<>();
        for (Topic topic : topics) {
            if (topic instanceof iPriority && topic.isActive()) {
                priorities.add((iPriority) topic);
            }
        }
        Collections.sort(priorities, (b, a) -> Float.compare(a.getPriorityScore(), b.getPriorityScore()));
        return priorities;
    }

    public List<Topic> getActivTopics() {
        List<Topic> activTopics = new ArrayList<>();
        for (Topic topic : topics) {
            if (topic.isActive())
                activTopics.add(topic);
        }
        return activTopics;
    }

    public List<Topic> getInactivTopics() {
        List<Topic> inactivTopics = new ArrayList<>();
        for (Topic topic : topics) {
            if (!topic.isActive())
                inactivTopics.add(topic);
        }
        return inactivTopics;
    }

}
