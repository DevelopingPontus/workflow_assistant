package com.example.chasky;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.example.chasky.llm.NexusLlm;

public class NexusLlmTest {
    NexusLlm llm = new NexusLlm();

    @Test
    public void shouldQueryLlmAndReturnString() {
        String response = llm.prompt("Hello");
        assertTrue(!response.isEmpty());
    }
}
