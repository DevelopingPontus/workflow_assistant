package com.example.chasky;

import com.example.chasky.llm.NexusLlm;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello World!");

        NexusLlm qwen = new NexusLlm();

        System.out.println(qwen.prompt("Name a JVM"));
    }

}
