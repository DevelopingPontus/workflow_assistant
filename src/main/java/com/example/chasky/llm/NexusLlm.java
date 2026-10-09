package com.example.chasky.llm;

import static org.modeljars.catalog.King3djbl_Nexus_Medical_Gguf_Q4_K_M.MODEL;

import com.integrallis.models.api.ModelPrompt;
import com.integrallis.models.api.SamplingOptions;
import com.integrallis.models.runtime.InferencePipeline;
import com.integrallis.models.runtime.chat.ChatMessage;
import java.util.List;

import org.modeljars.ModelJarRuntime;
import org.modeljars.ModelJars;

public class NexusLlm {
    float temperature = 0;
    int maxTokens = 128;

    public NexusLlm() {
    }

    public NexusLlm(float temperature, int maxTokens) {
        this.temperature = temperature;
        this.maxTokens = maxTokens;
    }

    SamplingOptions options = SamplingOptions.builder()
            .temperature(temperature).maxTokens(maxTokens).build();

    // Model will be cashed in a folder called .modeljars under user on mac.
    public String prompt(String query, String system) {
        ModelJarRuntime runtime = ModelJars.openRuntime(MODEL);
        InferencePipeline pipeline = runtime.pipeline();
        ModelPrompt prompt = runtime.chatTemplate().render(
                List.of(ChatMessage.system(system), ChatMessage.user(query)));
        return pipeline.generate(prompt, options);
    }

}