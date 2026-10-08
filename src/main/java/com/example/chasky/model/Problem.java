package com.example.chasky.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString(callSuper = true)
@Getter
@Setter 
public class Problem extends Topic {
    private String solution;

    public Problem(String description) {
        super(description);
    }
    
}
