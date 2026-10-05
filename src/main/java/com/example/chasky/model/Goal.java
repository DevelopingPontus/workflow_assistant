package com.example.chasky.model;

public class Goal extends Topic implements iPriority {

    private Importance importance;

    public Goal() {
        super();
        this.importance = Importance.NOT_SET;
    }

    @Override
    public float calculatePriorityScore() {
        return getImportance().getLevel();
    }

    @Override
    public Importance getImportance() {
        return importance;
    }

    @Override
    public void setImportance(Importance importance) {
        this.importance = importance;
    }

    @Override
    public float getPriorityScore() {
        return importance.getLevel();
    }
}
