package com.example.chasky.model;

import lombok.ToString;

@ToString
public enum Importance {
    NOT_SET(0),
    OPTIONAL(1),
    NICE_TO_HAVE(2),
    IMPORTANT(3),
    CRITICAL(4);

    private final int level;

    Importance(int level) {
        this.level = level;
    }

    public int getLevel() {
        return level;
    }
}
