package com.example.chasky.features.topic;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import com.example.chasky.common.interfaces.iPriority;
import com.example.chasky.features.user.User;

public class TopicService {

    /**
     * Get topics with a deadline and sort them by priority score.
     * 
     * @return
     */
    public List<iPriority> getTopicsByPrioriy(User user) {
        List<iPriority> priorities = new ArrayList<>();
        for (Topic topic : user.getTopics()) {
            if (topic instanceof iPriority && topic.getActive() == true) {
                priorities.add((iPriority) topic);
            }
        }
        Collections.sort(priorities, (b, a) -> Float.compare(a.getPriorityScore(), b.getPriorityScore()));
        return priorities;
    }

    public List<Topic> getActiveTopics(User user) {
        List<Topic> activTopics = new ArrayList<>();
        for (Topic topic : user.getTopics()) {
            if (topic.getActive())
                activTopics.add(topic);
        }
        return activTopics;
    }

    public List<Topic> getInactivTopics(User user) {
        List<Topic> inactivTopics = new ArrayList<>();
        for (Topic topic : user.getTopics()) {
            if (!topic.getActive())
                inactivTopics.add(topic);
        }
        return inactivTopics;
    }

    public Map<LocalDateTime, String> getLatestStatuses(Topic topic) {
        Map<LocalDateTime, String> statuses = topic.getStatusUpdates();
        if (statuses.size() > 10) {
            for (int i = 10; statuses.size() > 10; i--) {
                statuses = topic.getStatusUpdates().tailMap(LocalDateTime.now().minusDays(i));
            }
        }
        return statuses;
    }
}
