package com.example.chasky.model;

import java.time.LocalDateTime;
import java.util.TreeMap;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter 
public abstract class Topic {
    private String description;

    private LocalDateTime created;
    // I only want the latest status to show with toString
    @ToString.Exclude
    private TreeMap<LocalDateTime, String> statusUpdates;

    private String who;
    private String what;
    private String when;
    private String where;
    private String how;
    private String why;

    private boolean active;

    private Importance importance;

    private LocalDateTime nextTimeToCheckWithUser;

    public Topic(String descripiton) {
        this.description = descripiton;
        this.created = LocalDateTime.now();
        this.statusUpdates = new TreeMap<>();
        this.active = true;
        this.who = "";
        this.what = "";
        this.when = "";
        this.where = "";
        this.how = "";
        this.why = "";
        this.importance = Importance.NOT_SET;
    }

}
