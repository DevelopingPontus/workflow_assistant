package com.example.chasky.features.user;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.example.chasky.common.interfaces.iPriority;
import com.example.chasky.features.topic.Topic;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
public class User implements Serializable {
    private String email;

    /**
     * List of polymorphic topics
     */
    private List<Topic> topics;

    public User(String email) {
        this.email = email;
        this.topics = Collections.synchronizedList(new ArrayList<>());
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
            if (topic instanceof iPriority && topic.getActive() == true) {
                priorities.add((iPriority) topic);
            }
        }
        Collections.sort(priorities, (b, a) -> Float.compare(a.getPriorityScore(), b.getPriorityScore()));
        return priorities;
    }

    public List<Topic> getActiveTopics() {
        List<Topic> activTopics = new ArrayList<>();
        for (Topic topic : topics) {
            if (topic.getActive())
                activTopics.add(topic);
        }
        return activTopics;
    }

    public List<Topic> getInactivTopics() {
        List<Topic> inactivTopics = new ArrayList<>();
        for (Topic topic : topics) {
            if (!topic.getActive())
                inactivTopics.add(topic);
        }
        return inactivTopics;
    }

}
