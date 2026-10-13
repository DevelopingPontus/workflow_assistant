package com.example.chasky.llm.springJar;

import com.integrallis.models.api.SamplingOptions;
import com.integrallis.models.spring.ai.ModelsSpringAiChatModel;
import org.modeljars.ModelJars;
import org.springframework.ai.chat.client.ChatClient;

// import static org.modeljars.catalog.Qwen_Qwen2_5_3b_Instruct_Gguf_Q4_K_M.MODEL;
import static org.modeljars.catalog.Bartowski_Llama_3_2_3b_Instruct_Gguf_Q4_K_M.MODEL;

public class ChatClientConfig {

    public ChatClient.Builder chatClientBuilder() {
        var defaults = SamplingOptions.builder().build();
        // var loadOptions = ModelLoadOptions.builder()
        //         .backend(ModelBackend.JAVA)
        //         .build();
        var runtime = ModelJars.openRuntime(MODEL);

        var model = new ModelsSpringAiChatModel(
                runtime.model(),
                runtime.descriptor().alias(),
                runtime.chatTemplate(),
                defaults,
                runtime.descriptor().capabilities());

        return ChatClient.builder(model);
    }
}