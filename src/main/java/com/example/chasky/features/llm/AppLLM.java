package com.example.chasky.features.llm;
// package com.example.chasky;

// import java.time.LocalDateTime;
// import java.util.ArrayList;
// import java.util.List;
// import java.util.Scanner;

// import com.example.chasky.llm.springJar.LlmService;
// import com.example.chasky.model.Topic;
// import com.example.chasky.model.User;

// public class AppLLM {
//     private Scanner scanner;
//     private List<User> users;

//     private LlmService llmService;

//     public AppLLM(List<User> users, LlmService llmService) {
//         this.users = users;

//         this.llmService = llmService;
//     }

//     public void run() {
//     }


//     private List<Topic> getTopicsToCheckBackOn(User user) {
//         List<Topic> topics = user.getTopics();
//         List<Topic> topicsToCheckBackOn = new ArrayList<>();
//         if (topics.size() > 10) {
//             for (int i = 0; topicsToCheckBackOn.size() < 10; i++) {
//                 for (Topic topic : topics) {
//                     if (topic.getNextTimeToCheckWithUser().isBefore(LocalDateTime.now().plusDays(i))
//                             && topic.isActive()) {
//                         topicsToCheckBackOn.add(topic);
//                     }
//                 }
//             }
//         }
//         return topicsToCheckBackOn;
//     }
// }
