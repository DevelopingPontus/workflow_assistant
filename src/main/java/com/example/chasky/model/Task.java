package com.example.chasky.model;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString(callSuper = true)
@Getter
@Setter 
public class Task extends Topic implements iPriority {

    private LocalDateTime ends;

    public Task(String description) {
        super(description);
    }

    @Override
    public float getPriorityScore() {
        return this.calculatePriorityScore();
    }

    public float calculatePriorityScore() {
        float score = getImportance().getLevel();

        try {
            float daysLeft = (float) ChronoUnit.HOURS.between(LocalDateTime.now(), this.ends) / 24;
            System.out.println(daysLeft);
            score = this.getImportance().getLevel() * (7f / daysLeft);
        } catch (Exception e) {
            System.out.println("No ends date set for task. Defaulting to set IMPORTANCE_LEVEL");
        }

        return score;
    }

    @Override
    public String getWhat() {
        return super.getWhat();
    }

    @Override
    public Importance getImportance() {
        return super.getImportance();
    }

    @Override
    public void setImportance(Importance importance) {
        super.setImportance(importance);
    }

}
