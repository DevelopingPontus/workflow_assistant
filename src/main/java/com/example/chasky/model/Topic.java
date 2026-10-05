package com.example.chasky.model;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.TreeMap;

public class Topic {
    private LocalDateTime created;
    private Map<LocalDateTime, String> statusUpdates;

    private String who;
    private String what;
    private String when;
    private String where;
    private String how;
    private String why;

    private boolean isActive;

    public Topic() {
        this.created = LocalDateTime.now();
        this.statusUpdates = new TreeMap<>();
        this.isActive = true;
        this.who = "";
        this.what = "";
        this.when = "";
        this.where = "";
        this.how = "";
        this.why = "";
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public Map<LocalDateTime, String> getStatusUpdates() {
        return statusUpdates;
    }

    /**
     * Intended used when exiting a task that has ben edited.
     * @param statusUpdates
     */
    public void setStatusUpdates(Map<LocalDateTime, String> statusUpdates) {
        this.statusUpdates = statusUpdates;
    }

    public String getWho() {
        return who;
    }

    public void setWho(String who) {
        this.who = who;
    }

    public String getWhat() {
        return what;
    }

    public void setWhat(String what) {
        this.what = what;
    }

    public String getWhen() {
        return when;
    }

    public void setWhen(String when) {
        this.when = when;
    }

    public String getWhere() {
        return where;
    }

    public void setWhere(String where) {
        this.where = where;
    }

    public String getHow() {
        return how;
    }

    public void setHow(String how) {
        this.how = how;
    }

    public String getWhy() {
        return why;
    }

    public void setWhy(String why) {
        this.why = why;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean isActive) {
        this.isActive = isActive;
    }

}
