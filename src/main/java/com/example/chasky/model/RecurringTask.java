package com.example.chasky.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString(callSuper = true)
@Getter
@Setter 
public class RecurringTask extends Topic implements iPriority {


    public RecurringTask(String description) {
        super(description);
    }

    @Override
    public float getPriorityScore() {
        return getImportance().getLevel();
    }

    @Override 
    public String getWhat() {
        return getWhat();
    }

    @Override
    public Importance getImportance() {
        return getImportance();
    }

    @Override
    public void setImportance(Importance importance) {
        this.setImportance(importance);
    }
}
