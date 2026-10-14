package com.example.chasky;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;
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
    private AnimateSlide animate;

    public App() {
        this.scanner = new Scanner(System.in);
        // TODO: Make users persistent across runs
        this.users = new ArrayList<>();

        this.mockData = new MockData(users);
        this.animate = new AnimateSlide();
        animate.setupOutputCapture();
    }

    public void run() {
        userOptionsLoop();
    }

    private void userOptionsLoop() {
        printSection("Users");
        if (users.isEmpty()) {
            System.out.println("No users registered. Please create one first. ");
            newUserLoop();
        } else {
            for (User user : users) {
                System.out.printf("%-3d | Email: %s%n",
                        users.indexOf(user), user.getEmail());
            }
            System.out.println("");
            System.out.println("Enter INDEX of user in list to open or enter NEW to crete new user. ");
        }
        
        String pick = scanner.nextLine().trim();
        animate.fromSlide();

        if (pick.equalsIgnoreCase("NEW")) {
            newUserLoop();
        } else {
            try {
                int index = Integer.parseInt(pick);
                if (index < users.size()) {
                    User user = users.get(index);
                    topicOptionsLoop(user);
                } else {
                    System.out.println("Index out of bounds. Expected: 0 - " + (users.size() - 1));
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid INDEX. " + pick);
            }
        }
        userOptionsLoop();
    }

    private void newUserLoop() {
        printSection("New User");
        System.out.println("");
        System.out.println("Enter EMAIL of new user or BACK");

        animate.toSlide();
        animate.slideRight();
        String email = scanner.nextLine().trim();
        animate.fromSlide();

        if (email.equalsIgnoreCase("BACK")) {
            animate.slideRightReverse();
            return;
        }

        // Checks if email is valid format.
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@" +
                "(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        Pattern p = Pattern.compile(emailRegex);
        if (email != null && p.matcher(email).matches()) {
            User newUser = new User(email);
            users.add(newUser);
            topicOptionsLoop(newUser);
        } else {
            System.out.println("Invalid email format. " + email);
            newUserLoop();
        }
    }

    private void topicOptionsLoop(User user) {
        List<iPriority> topics = user.getTopicsByPrioriy();

        printSection("Topics");
        printSubsection("Indexes by Priority score");
            for (int i = 0; i < topics.size(); i++) {
                iPriority priority = topics.get(i);
                System.out.printf("%-3d | Topic: %-30s | Priority Score: %s%n",
                        i, priority.getWhat(), priority.getPriorityScore());
            }
        System.out.println("");
        System.out.println("Enter INDEX of topic to open, NEW or BACK: ");

        String pick = scanner.nextLine().trim();

        if (pick.equalsIgnoreCase("NEW")) {
            newTopicLoop(user);
        } else if (pick.equalsIgnoreCase("BACK")) {
            return;
        } else {
            try {
                int index = Integer.parseInt(pick);
                Topic topic = ((Topic) user.getTopicsByPrioriy().get(index));
                editTopicLoop(topic);
            } catch (NumberFormatException e) {
                System.out.println("");
                System.out.println("Invalid input: " + pick + ". Please enter a INDEX of topic, NEW or BACK. ");
            }
        }
        topicOptionsLoop(user);
    }

    private void newTopicLoop(User user) {
        printSection("New Topic for " + user.getEmail());
        printSubsection("Options");
        System.out.println("\nIndex:1 Task(Has a deadline), " +
                "\nIndex:2 RecurringTask(Does not have a deadline), " +
                "\nIndex:3 Problem(Something you want to improve or solve). ");
        System.out.println("");
        System.out.println("Enter INDEX of the topic type you want to create or enter BACK. ");

        String pick = scanner.nextLine().trim();

        if (pick.equalsIgnoreCase("BACK")) {
            return;
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

        printSection(topic.getClass().getSimpleName() + ": " + topic.getWhat());

        printSubsection("Fields and Values");
        List<String> fieldNames = new ArrayList<>(getterByFieldName.keySet().stream().toList());
        Collections.sort(fieldNames);
        for (String field : fieldNames) {
            try {
                var value = getterByFieldName.get(field).invoke(topic);
                System.out.printf("%-30s : %s%n", field, value);
            } catch (IllegalAccessException | InvocationTargetException e) {
                System.out.println("Could not read value for " + field);
            }
        }
        printSubsection("Latest statuses");
        getLatestStatuses(topic).forEach((timestamp, status) -> System.out.printf("%-30s : %s%n", timestamp, status));
        printSubsection("Options");
        System.out.println("FIELD_NAMEs:" + setterByFieldName.keySet());
        System.out.println("");
        System.out.println("Enter a FIELD_NAME to edit or BACK: ");

        String pick = scanner.nextLine().trim();

        if (pick.equalsIgnoreCase("BACK")) {
            return;
        }

        if (setterByFieldName.containsKey(pick)) {
            Method setter = setterByFieldName.get(pick);
            Class<?> paramType = setter.getParameterTypes()[0];
            Object[] enumConstants = null;

            System.out.println("");
            System.out.printf("Enter new VALUE for %s:%n", pick.toUpperCase());

            // Show options based on parameter type
            if (paramType.isEnum()) {
                enumConstants = paramType.getEnumConstants();
                System.out.println("Valid options:");
                for (int i = 0; i < enumConstants.length; i++) {
                    System.out.printf("  %d. %s%n", i, enumConstants[i]);
                }
                System.out.println("");
                System.out.print("Enter index value: ");
                try {
                    int index = scanner.nextInt();
                    Object selectedValue = enumConstants[index];
                    setter.invoke(topic, selectedValue);
                } catch (Exception e) {
                    System.out.println("Invalid input. ");
                }
            } else if (paramType == boolean.class) {
                System.out.println("Valid options: true | false");
                try {
                    Boolean boolValue = scanner.nextBoolean();
                    setter.invoke(topic, boolValue);
                } catch (Exception e) {
                    System.out.println("Invalid input. ");
                }
            } else if (paramType == int.class || paramType == Integer.class) {
                System.out.println("Expected: Integer value");
                try {
                    int intValue = scanner.nextInt();
                    setter.invoke(topic, intValue);
                } catch (Exception e) {
                    System.out.println("Invalid input. ");
                }
            } else if (paramType == String.class) {
                System.out.println("Expected: Text string");
                try {
                    String stringValue = scanner.nextLine().trim();
                    setter.invoke(topic, stringValue);
                } catch (Exception e) {
                    System.out.println("Error setting value of " + pick);
                }
            } else if (paramType == LocalDateTime.class) {
                System.out.println("Expected: Date and time (yyyy-MM-dd HH:mm:ss)");
                try {
                    String input = scanner.nextLine().trim();
                    LocalDateTime dateTimeValue = LocalDateTime.parse(input,
                            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
                    setter.invoke(topic, dateTimeValue);
                } catch (Exception e) {
                    System.out.println("Error setting value of " + pick);
                }
            } else if (paramType == TreeMap.class) {
                System.out.println("Expected: Status update");
                try {
                    // Get the existing TreeMap using getter
                    TreeMap<LocalDateTime, String> statusUpdates = (TreeMap<LocalDateTime, String>) topic
                            .getStatusUpdates();
                    System.out.println("");
                    System.out.println("Enter status message:");
                    String statusMessage = scanner.nextLine().trim();

                    statusUpdates.put(LocalDateTime.now(), statusMessage);

                    setter.invoke(topic, statusUpdates);

                    System.out.println("Status update added!");

                } catch (Exception e) {
                    System.out.println("Error adding status update: " + e.getMessage());
                }
            } else {
                System.out.printf("Expected: %s%n", paramType.getSimpleName());
                try {
                    var inputValue = scanner.nextLine();
                    setter.invoke(topic, inputValue);
                } catch (Exception e) {
                    System.out.println("Error setting value of " + pick);
                }
            }
        }
        editTopicLoop(topic);
    }

    // Helper functions
    private void printSection(String title) {
        System.out.println("\n".repeat(50) + "=".repeat(150));
        System.out.println("  " + title);
        System.out.println("=".repeat(150));
    }

    private void printSubsection(String subtitle) {
        System.out.println("\n  " + subtitle);
        System.out.println("  " + "-".repeat(148));
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
