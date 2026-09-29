package com.example.chasky;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.IOException;

import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class AppTest {
    LocalLLM llm = new LocalLLM();

    @Test
    public void shouldQueryLlmAndReturnAString() {
        try {
            String query = "Hello!";
            String res = llm.query(query);
            assertNotNull(res);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
