package com.example.chasky;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Pattern;

import com.example.chasky.model.Problem;
import com.example.chasky.model.RecurringTask;
import com.example.chasky.model.Task;
import com.example.chasky.model.Topic;
import com.example.chasky.model.User;
import com.example.chasky.model.iPriority;

public class App {
    private Scanner scanner;
    private List<User> users;

    // private AppLLM appLLM;

    private MockData mockData;

    public App() {
        this.scanner = new Scanner(System.in);
        // TODO: Make users persistent across runs
        this.users = new ArrayList<>();

        this.mockData = new MockData(users);
    }

    public void run() {
        userOptionsLoop();
    }

    private void userOptionsLoop() {
        // Screen options
        String what = "*** Users ***";
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
            System.out.println(how);
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
        userOptionsLoop();
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
        List<iPriority> topics = user.getTopicsByPrioriy();

        System.out.println("");
        System.out.println("*** Topics ***");
        System.out.println("--- Indexes by Priority score ---");
        for (int i = 0; i < topics.size(); i++) {
            iPriority priority = topics.get(i);
            System.out.printf("%-3d | Topic: %-60s | Priority Score: %s%n",
                    i, priority.getWhat(), priority.getPriorityScore());
        }
        System.out.println("");
        System.out.println("Enter INDEX of topic to open, NEW or BACK: ");

        String pick = scanner.nextLine().trim();

        if (pick.equalsIgnoreCase("NEW")) {
            newTopicLoop(user);
        } else if (pick.equalsIgnoreCase("BACK")) {

        } else {
            try {
                int index = Integer.parseInt(pick);
                Topic topic = ((Topic) user.getTopicsByPrioriy().get(index));
                editTopicLoop(topic);
            } catch (NumberFormatException e) {
                System.out.println("Invalid input: " + pick + ". Please enter a INDEX of topic, NEW or BACK. ");
            }
        }
        topicOptionsLoop(user);
    }

    private void newTopicLoop(User user) {
        System.out.println("");
        System.out.println("*** New Topic for " + user.getEmail() + " ***");
        System.out.println("--- Options ---");
        System.out.println("\nIndex:1 Task(Has a deadline), " +
                "\nIndex:2 RecurringTask(Does not have a deadline), " +
                "\nIndex:3 Problem(Something you want to improve or solve). ");
        System.out.println("");
        System.out.println("Enter INDEX of the topic type you want to create or enter BACK. ");

        String pick = scanner.nextLine().trim();

        if (pick.equalsIgnoreCase("BACK")) {
        } else if (pick.equals("1")) {
            Task task = new Task();
            user.addTopic(task);
            editTopicLoop((Topic) task);
        } else if (pick.equals("2")) {
            RecurringTask recurringTask = new RecurringTask();
            user.addTopic(recurringTask);
            editTopicLoop((Topic) recurringTask);
        } else if (pick.equals("3")) {
            Problem problem = new Problem();
            user.addTopic(problem);
            editTopicLoop((Topic) problem);
        } else {
            System.out.println("Invalid input: " + pick);
            newTopicLoop(user);
        }
    }

    private void editTopicLoop(Topic topic) {
        Map<String, Method> setterByFieldName = extractSettersByFieldName(topic.getClass(), new HashMap<>());
        Map<String, Method> getterByFieldName = extractGettersByFieldName(topic.getClass(), new HashMap<>());

        System.out.println("");
        System.out.println("*** " + topic.getClass().getSimpleName() + ": " + topic.getWhat() + " ***");

        System.out.println("--- Fields and Values ---");
        for (String field : getterByFieldName.keySet()) {
            try {
                var value = getterByFieldName.get(field).invoke(topic);
                System.out.printf("%-30s : %s%n", field, value);
            } catch (IllegalAccessException | InvocationTargetException e) {
                System.out.println("Could not read value for " + field);
            }
        }
        System.out.println("--- Latest statuses ---");
        getLatestStatuses(topic).forEach((timestamp, status) -> System.out.printf("%-30s : %s%n", timestamp, status));
        System.out.println("--- Options ---");
        System.out.println("FIELD_NAMEs:" + setterByFieldName.keySet());
        System.out.println("");
        System.out.println("Enter FIELD_NAME to edit or EXIT: ");

        String pick = scanner.nextLine().trim();

        if (setterByFieldName.containsKey(pick)) {
            Method setter = setterByFieldName.get(pick);
            Class<?> paramType = setter.getParameterTypes()[0];

            System.out.println("");
            System.out.printf("Enter new VALUE for %s:%n", pick.toUpperCase());

            // Show options based on parameter type
            if (paramType.isEnum()) {
                System.out.println("Valid options:");
                for (Object constant : paramType.getEnumConstants()) {
                    System.out.printf("  • %s%n", constant);
                }
            } else if (paramType == boolean.class) {
                System.out.println("Valid options: true | false");
            } else if (paramType == int.class || paramType == Integer.class) {
                System.out.println("Expected: Integer value");
            } else if (paramType == String.class) {
                System.out.println("Expected: Text string");
            } else {
                System.out.printf("Expected: %s%n", paramType.getSimpleName());
            }

            String value = scanner.nextLine().trim();

            if (!value.isBlank()) {
                try {
                    setter.invoke(topic, value);
                    editTopicLoop(topic);
                } catch (Exception e) {
                    System.out.println("Error setting value of " + pick);
                    editTopicLoop(topic);
                }
            }
        }

        // if (topic.getNextTimeToCheckWithUser() == null) {
        // topic.setNextTimeToCheckWithUser(LocalDateTime.now().minusDays(1));
        // }
        // if (pick.equalsIgnoreCase("EXIT")) {
        // if (topic.getNextTimeToCheckWithUser().isBefore(LocalDateTime.now()) &&
        // topic.isActive()) {
        // System.out.println(topic.toString());
        // System.out.println(getLatestStatuses(topic));

        // System.out.println("Set how many hours untill next check in.");
        // String newDateTime = scanner.nextLine().trim();
        // try {
        // topic.setNextTimeToCheckWithUser(LocalDateTime.now().plusHours(Long.parseLong(newDateTime)));
        // } catch (Exception e) {
        // System.out.println("Error parsing new LocalDateTime. Setting next check in 1
        // day.");
        // topic.setNextTimeToCheckWithUser(LocalDateTime.now().plusDays(1));
        // }
        // }
        // } else {
        // System.out.println("Invalid input. Please try again.");
        // editTopicLoop(topic);
        // }
    }

    // Helper functions

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
            try {
                setterByFieldName.remove("statusUpdates");
            } catch (Exception e) {
                // TODO: handle exception
            }
        }
        return setterByFieldName;
    }

    private <T> Map<String, Method> extractGettersByFieldName(Class<T> clazz, Map<String, Method> getterByFieldName) {
        Class<?> currentClass = clazz;

        while (currentClass != null && currentClass != Object.class) {
            Field[] fields = currentClass.getDeclaredFields();

            for (Field field : fields) {
                String fieldName = field.getName();
                try {
                    String methodName = "get" + capitalizeFirstLetter(fieldName);
                    Method method = clazz.getMethod(methodName);
                    getterByFieldName.put(fieldName, method);
                } catch (NoSuchMethodException e) {
                    System.out.println("Getter method for " + fieldName + " not found.");
                }
            }
            currentClass = currentClass.getSuperclass();
            try {
                getterByFieldName.remove("statusUpdates");
            } catch (Exception e) {
                // TODO: handle exception
            }
        }
        return getterByFieldName;
    }

    private String capitalizeFirstLetter(String field) {
        String methodName = Character.toUpperCase(field.charAt(0)) + field.substring(1);
        return methodName;
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
}
