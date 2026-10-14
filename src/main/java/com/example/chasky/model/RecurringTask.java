package com.example.chasky.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString(callSuper = true)
@Getter
@Setter 
public class RecurringTask extends Topic implements iPriority {


    public RecurringTask() {
    }

    @Override
    public float getPriorityScore() {
        return super.getImportance().getLevel();
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
