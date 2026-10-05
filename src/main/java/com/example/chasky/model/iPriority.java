package com.example.chasky.model;


/**
 * Interface for topics that have priority.
 */
public interface iPriority {

    Importance getImportance();

    void setImportance(Importance importance);

    /**
     * Used as polymorphic method to calculate the priority score of a topic dependting on if it has deadline in it's class.
     * 
     * @return the priority score
     */
    float getPriorityScore();

    float calculatePriorityScore();

}