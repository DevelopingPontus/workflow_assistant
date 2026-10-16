package com.example.chasky.features.llm.springJar;

import java.util.Map;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.PromptTemplate;

import com.example.chasky.features.topic.Topic;


public class LlmService {

    private final ChatClient chatClient;

    public LlmService() {
        ChatClientConfig chatClientConfig = new ChatClientConfig();
        ChatClient.Builder builder = chatClientConfig.chatClientBuilder();
        this.chatClient = builder.build();
    }

    public String summarize(Topic topic, String entity) {
        PromptTemplate pt = new PromptTemplate("""
                Summarize update reported by user.
                """);
        Prompt p = pt.create(Map.of("entity", entity));

        return this.chatClient.prompt(p)
                .call()
                .content();
    }
}