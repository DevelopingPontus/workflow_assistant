package com.example.chasky;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import com.example.chasky.llm.NexusLlm;
import com.example.chasky.model.Importance;
import com.example.chasky.model.Problem;
import com.example.chasky.model.RecurringTask;
import com.example.chasky.model.Task;
import com.example.chasky.model.Topic;
import com.example.chasky.model.User;

public class AppLLM {
    private Scanner scanner;
    private List<User> users;

    private NexusLlm llm;

    public AppLLM() {
        this.scanner = new Scanner(System.in);
        // TODO: Make users persistent across runs
        this.users = new ArrayList<>();

        llm = new NexusLlm();

        mockData(users);
    }

    private void mockData(List<User> users) {
        String mockTaskDescription = "You've got 47 email attachments and handwritten receipts from the past three months\n"
                +
                " scattered across your desk, downloads folder, and that one notebook you can't find.\n" +
                " They need to be categorized by client, logged into the spreadsheet, and submitted to accounting by Friday.";

        User p = new User("p@gamil.com");
        User q = new User("q@gamil.com");
        this.users.add(p);
        this.users.add(q);
        List<Task> t = List.of(new Task("Do this"), new Task("Do that"));
        t.get(0).setImportance(Importance.CRITICAL);
        t.get(0).setEnds(LocalDateTime.now().plusDays(2));
        t.get(0).setWhat("This");
        t.get(1).setImportance(Importance.CRITICAL);
        t.get(1).setEnds(LocalDateTime.now().plusDays(7));
        t.get(1).setWhat("That");

        p.getTopics().addAll(t);

        identifyTopics(users.get(0), mockTaskDescription);
    }

    public void run() {
        topicOptionsLoop(users.get(0));
    }

    private void optionsLoop() {
        // Screen options
        String what = "Users:";
        // if
        String ifNoUser = "No users registered. Please create one first. ";
        // else
        List<String> userIndexes = new ArrayList<>();
        for (User user : users) {
            userIndexes.add("\n" + "Index:" + users.indexOf(user) + " " + user.getEmail());
        }
        String how = "Enter INDEX of user in list to read or enter NEW to crete new user. ";
        // if
        String invalidIndex = "Invalid INDEX. ";

        // Logic
        System.out.println("");
        System.out.println(what);
        if (users.isEmpty()) {
            System.out.println(ifNoUser);
            newUserLoop();
        } else {
            System.out.println(userIndexes);
            printInstructions(how);
        }

        String pick = scanner.next().trim();

        if (pick.equalsIgnoreCase("NEW")) {
            newUserLoop();
        } else {
            try {
                int index = Integer.parseInt(pick);
                User user = users.get(index);
                topicOptionsLoop(user);
            } catch (NumberFormatException e) {
                System.out.println(invalidIndex);
            }
        }
        optionsLoop();
    }

    private void newUserLoop() {
        // Screen options
        String how = "Enter name of new user or BACK";
        // if
        String invalidFormat = "Invalid email format. ";

        // Logic
        System.out.println("");
        System.out.println(how);

        String email = scanner.nextLine().trim();

        // Checks if email is valid format.
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@" +
                "(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        Pattern p = Pattern.compile(emailRegex);
        if (email != null && p.matcher(email).matches()) {
            User newUser = new User(email);
            users.add(newUser);
            topicOptionsLoop(newUser);
        } else if (email != null && email.equals("BACK")) {

        } else {
            System.out.println(invalidFormat);
            newUserLoop();
        }
    }

    private void topicOptionsLoop(User user) {
        // Screen options
        String what = "Topic index:";
        List<String> topicIndexes = new ArrayList<>();
        List<Topic> toCheckBackOn = getTopicsToCheckBackOn(user);
        for (Topic priority : toCheckBackOn) {
            topicIndexes.add("\n" + "Index:" + toCheckBackOn.indexOf(priority) + " Topic:"
                    + priority.getWhat() + " Time to check with user:"
                    + priority.getNextTimeToCheckWithUser());
        }
        String how = "Enter INDEX of topic to open, NEW or BACK. ";
        // if
        String invalidInput = "Invalid input. Please enter a INDEX of topic, NEW or BACK. ";

        // Logic
        System.out.println("");
        System.out.println(what);
        System.out.println(topicIndexes);
        System.out.println(how);

        String pick = llm.prompt(what + topicIndexes + how);
        // String pick = scanner.nextLine().trim();

        // if (pick.equalsIgnoreCase("NEW")) {
        // newTopicLoop(user);
        // } else
        if (pick.equalsIgnoreCase("BACK")) {

        } else {
            try {
                int index = Integer.parseInt(pick);
                Topic topic = ((Topic) user.getTopicsByPrioriy().get(index));
                editTopicLoop(topic);
            } catch (NumberFormatException e) {
                System.out.println(invalidInput);
            }
        }
        topicOptionsLoop(user);
    }

    private void identifyTopics(User user, String description) {
        String response = llm
                .prompt("Text: " + description)
                .trim();

        List<String> sections = Arrays.stream(response.split("(?m)^\\d+\\.\\s*"))
                .map(String::trim)
                .filter(s -> !s.isEmpty())
                .collect(Collectors.toList());

        System.out.println("");
        System.out.println("");
        System.out.println(sections.toString());

        for (String topic : sections) {
            newTopicLoop(user, topic);
        }
    }

    private void newTopicLoop(User user, String description) {
        // Screen options
        String what = "Create a new topic based on description: ";
        String indexes = "Index:1 Task(Has a deadline), " + "Index:2 RecurringTask(Does not have a deadline), " +
                "Index:3 Problem(Something you want to improve or solve). ";
        String how = "Enter INDEX of the topic type you want to create or enter BACK. ";
        // if
        String howDescription = "Please enter the description of your task:";
        // else
        String invalidInput = "Invalid input. ";

        // Logic
        printInstructions(indexes);
        System.out.println(how);

        String pick = llm.prompt(what + description + indexes + how);
        // String pick = scanner.nextLine().trim();

        if (pick.equalsIgnoreCase("BACK")) {
        } else if (pick.equals("1")) {
            System.out.println(howDescription);
            Task task = new Task(description);
            user.addTopic(task);
            editTopicLoop((Topic) task);
        } else if (pick.equals("2")) {
            System.out.println(howDescription);
            RecurringTask recurringTask = new RecurringTask(description);
            user.addTopic(recurringTask);
            editTopicLoop((Topic) recurringTask);
        } else if (pick.equals("3")) {
            System.out.println(howDescription);
            Problem problem = new Problem(description);
            user.addTopic(problem);
            editTopicLoop((Topic) problem);
        } else {
            System.out.println(invalidInput);
        }
    }

    private void editTopicLoop(Topic topic) {
        Map<String, Method> setterByFieldName = getSettersByFieldName(topic);

        // Static screen options
        String how = "Enter FIELD_NAME to rewrite a field value or EXIT. ";

        // Logic
        System.out.println("");
        System.out.println(topic.toString());
        System.out.println(getLatestStatuses(topic));
        System.out.println("FIELD_NAMES:" + setterByFieldName.keySet());
        System.out.println(how);

        String pick = llm.prompt(topic.toString() + getSettersByFieldName(topic) + "FIELD_NAMES:"
                + setterByFieldName.keySet() + how);
        // String pick = scanner.nextLine().trim();

        if (setterByFieldName.containsKey(pick)) {
            Method setter = setterByFieldName.get(pick);

            System.out.println("");
            System.out.println(topic.toString());
            System.out.println(getLatestStatuses(topic));
            System.out.println("Enter new value for " + pick + ":");

            String value = llm.prompt(topic.toString() + getSettersByFieldName(topic) + "Enter new value for " + pick
                    + ":");
            // String value = scanner.nextLine().trim();

            if (!value.isBlank()) {
                try {
                    setter.invoke(topic, value);
                    editTopicLoop(topic);
                } catch (Exception e) {
                    System.out.println("Error setting value of " + pick);
                }
            }
        }

        if (topic.getNextTimeToCheckWithUser() == null) {
            topic.setNextTimeToCheckWithUser(LocalDateTime.now().minusDays(1));
        }
        if (pick.equalsIgnoreCase("EXIT")) {
            if (topic.getNextTimeToCheckWithUser().isBefore(LocalDateTime.now()) && topic.isActive()) {
                System.out.println(topic.toString());
                System.out.println(getLatestStatuses(topic));
                System.out.println("Set how many hours untill next check with user.");

                String newDateTime = llm.prompt(
                        topic.toString() + getLatestStatuses(topic)
                                + "Set how many hours untill next check with user.");
                // String newDateTime = scanner.nextLine().trim();
                try {
                    topic.setNextTimeToCheckWithUser(LocalDateTime.now().plusHours(Long.parseLong(newDateTime)));
                } catch (Exception e) {
                    System.out.println("Error parsing new LocalDateTime. Setting next check in 1 day.");
                    topic.setNextTimeToCheckWithUser(LocalDateTime.now().plusDays(1));
                }
            }
        } else {
            System.out.println("Invalid input. Please try again.");
            editTopicLoop(topic);
        }
    }

    // Helper functions
    private Map<String, Method> getSettersByFieldName(Topic topic) {
        Map<String, Method> setterByFieldName = new HashMap<>();

        setterByFieldName = extractSettersByFieldName(topic.getClass(), new HashMap<>());

        return setterByFieldName;
    }

    private <T> Map<String, Method> extractSettersByFieldName(Class<T> clazz, Map<String, Method> setterByFieldName) {
        Class<?> currentClass = clazz;

        while (currentClass != null && currentClass != Object.class) {
            Field[] fields = currentClass.getDeclaredFields();

            for (Field field : fields) {
                String fieldName = field.getName();
                try {
                    String methodName = "set" + capitalizeFirstLetter(fieldName);
                    Method method = clazz.getMethod(methodName, field.getType());
                    setterByFieldName.put(fieldName, method);
                } catch (NoSuchMethodException e) {
                    System.out.println("Setter method for " + fieldName + " not found.");
                }
            }
            currentClass = currentClass.getSuperclass();
        }
        return setterByFieldName;
    }

    private String capitalizeFirstLetter(String field) {
        String methodName = Character.toUpperCase(field.charAt(0)) + field.substring(1);
        return methodName;
    }

    private void printInstructions(String options) {
        System.out.println("");
        System.out.println(options);
    }

    private Map<LocalDateTime, String> getLatestStatuses(Topic topic) {
        Map<LocalDateTime, String> statuses = topic.getStatusUpdates();
        if (statuses.size() > 10) {
            for (int i = 10; statuses.size() > 10; i--) {
                statuses = topic.getStatusUpdates().tailMap(LocalDateTime.now().minusDays(i));
            }
        }
        return statuses;
    }

    private List<Topic> getTopicsToCheckBackOn(User user) {
        List<Topic> topics = user.getTopics();
        List<Topic> topicsToCheckBackOn = new ArrayList<>();
        if (topics.size() > 10) {
            for (int i = 0; topicsToCheckBackOn.size() < 10; i++) {
                for (Topic topic : topics) {
                    if (topic.getNextTimeToCheckWithUser().isBefore(LocalDateTime.now().plusDays(i))
                            && topic.isActive()) {
                        topicsToCheckBackOn.add(topic);
                    }
                }
            }
        }
        return topicsToCheckBackOn;
    }
}
