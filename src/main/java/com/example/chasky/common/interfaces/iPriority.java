package com.example.chasky.common.interfaces;

import com.example.chasky.common.enums.Importance;

/**
 * Interface for topics that have priority.
 */
public interface iPriority {

    String getWhat();

    Importance getImportance();

    void setImportance(Importance importance);

    /**
     * Used as polymorphic method to calculate the priority score of a topic dependting on if it has deadline in it's class.
     * 
     * @return the priority score
     */
    float getPriorityScore();

}