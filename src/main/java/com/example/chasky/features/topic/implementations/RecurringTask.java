package com.example.chasky.features.topic.implementations;

import com.example.chasky.common.enums.Importance;
import com.example.chasky.common.interfaces.iPriority;
import com.example.chasky.features.topic.Topic;

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
