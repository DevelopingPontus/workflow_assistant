package com.example.chasky.model;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Task extends Topic implements iPriority {

    private static final Logger logger = LoggerFactory.getLogger(Task.class);

    private LocalDateTime ends;
    private boolean isCompleted;

    private Importance importance;

    public Task() {
        super();
        this.isCompleted = false;
        this.importance = Importance.NOT_SET;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public void setCompleted(boolean isCompleted) {
        this.isCompleted = isCompleted;
    }

    @Override
    public float getPriorityScore() {
        return calculatePriorityScore();
    }

    public float calculatePriorityScore() {
        float score = getImportance().getLevel();

        if (this.getWhen() == null) {
            logger.warn("Task '{}' has no end date set", this.getWhat());
        }

        if (this.getImportance() == null) {
            logger.warn("Task '{}' has no importance set", this.getWhat());
        }

        try {
            int daysLeft = (int) ChronoUnit.DAYS.between(LocalDateTime.now(), this.ends);
            score = this.getImportance().getLevel() * (1 / daysLeft);
            logger.debug("Calculated priority score for '{}': {}", this.getWhat(), this.getPriorityScore());
        } catch (Exception e) {
            logger.error("Error calculating priority score for task '{}'", this.getWhat(), e);
        }

        return score;
    }

    @Override
    public Importance getImportance() {
        return importance;
    }

    @Override
    public void setImportance(Importance importance) {
        this.importance = importance;
    }

}
