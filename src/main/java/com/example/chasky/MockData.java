package com.example.chasky;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

import com.example.chasky.model.Importance;
import com.example.chasky.model.Problem;
import com.example.chasky.model.RecurringTask;
import com.example.chasky.model.Task;
import com.example.chasky.model.Topic;
import com.example.chasky.model.User;

public class MockData {

    public MockData(List<User> users) {
        User p = new User("p@gamil.com");
        User q = new User("q@gamil.com");
        users.add(p);
        users.add(q);

        List<Topic> userTopics = new ArrayList<>();
        userTopics.addAll(mockTasks());
        userTopics.addAll(mockRecurringTasks());
        userTopics.addAll(mockProblems());
        p.setTopics(userTopics);

    }

    private List<Topic> mockTasks() {
        List<Topic> tasks = new ArrayList<>();
        // Task 1: Plan vacation trip
        Task task1 = new Task();
        task1.setHow("Research destinations, book flights, reserve hotels, create itinerary");
        task1.setWhat("European vacation planning");
        task1.setWhen("Next 2 weeks before flights depart");
        task1.setWhere("Home office / travel booking websites");
        task1.setWho("Self and family");
        task1.setWhy("Need to book accommodations before prices increase");
        task1.setEnds(LocalDateTime.now().plusDays(3));
        task1.setImportance(Importance.CRITICAL);
        TreeMap<LocalDateTime, String> statusUpdates = new TreeMap<>();
        statusUpdates.put(LocalDateTime.now().minusHours(48),
        "STARTED - Researched top destinations: Rome, Florence, Venice. Found good flight deals on Skyscanner.");
        statusUpdates.put(LocalDateTime.now().minusHours(36),
        "IN PROGRESS - Booked round-trip flights for 2 adults + 2 children. Total: €850/person. Departure June 15, return June 29.");
        statusUpdates.put(LocalDateTime.now().minusHours(24),
        "IN PROGRESS - Reserved 4-star hotel in Rome city center for 7 nights (€120/night). Got 10% early booking discount.");
        statusUpdates.put(LocalDateTime.now().minusHours(18),
        "IN PROGRESS - Booked Airbnb apartment in Florence for 4 nights. Great location, near Duomo, €95/night.");
        statusUpdates.put(LocalDateTime.now().minusHours(12),
        "IN PROGRESS - Created detailed 14-day itinerary. Day 1-3: Rome, Day 4-7: Florence, Day 8-10: Venice, Day 11-14: Amalfi Coast.");
        statusUpdates.put(LocalDateTime.now().minusHours(6),
        "IN PROGRESS - Booked train tickets Rome→Florence→Venice. Reserved guided tours for Colosseum and Uffizi Gallery.");
        statusUpdates.put(LocalDateTime.now().minusHours(2),
        "NEARLY COMPLETE - Booked ferry from Venice to Burano island for Day 9. Confirmed all hotel/Airbnb reservations.");
        task1.setStatusUpdates(statusUpdates);
        

        // Task 2: Fix leaky kitchen faucet
        Task task2 = new Task();
        task2.setHow("Watch repair video, gather tools, replace cartridge or washers");
        task2.setWhat("Fix water leak from kitchen faucet");
        task2.setWhen("This weekend");
        task2.setWhere("Home kitchen");
        task2.setWho("Self (or call plumber if needed)");
        task2.setWhy("Water leak is wasting money and damaging the cabinet");
        task2.setEnds(LocalDateTime.now().plusDays(5));
        task2.setStatusUpdates(new TreeMap<>());
        task2.setImportance(Importance.CRITICAL);

        // Task 3: Update resume and portfolio
        Task task3 = new Task();
        task3.setHow("Add recent projects, update skills, improve formatting, add GitHub link");
        task3.setWhat("Resume and portfolio refresh");
        task3.setWhen("Complete by end of month");
        task3.setWhere("Google Docs / personal website");
        task3.setWho("Self");
        task3.setWhy("Preparing for potential job opportunities");
        task3.setEnds(LocalDateTime.now().plusDays(14));
        task3.setStatusUpdates(new TreeMap<>());
        task3.setImportance(Importance.IMPORTANT);

        // Task 4: Learn Spanish language
        Task task4 = new Task();
        task4.setHow("Practice daily with Duolingo, watch Spanish movies, join conversation group");
        task4.setWhat("Spanish language improvement");
        task4.setWhen("30 minutes daily");
        task4.setWhere("Home / online");
        task4.setWho("Self");
        task4.setWhy("Want to be fluent before traveling to Latin America next year");
        task4.setEnds(LocalDateTime.now().plusDays(30));
        task4.setStatusUpdates(new TreeMap<>());
        task4.setImportance(Importance.NICE_TO_HAVE);

        // Task 5: Explore new hobby (photography)
        Task task5 = new Task();
        task5.setHow("Watch online tutorials, practice with DSLR, create portfolio of sample photos");
        task5.setWhat("Photography skill learning and exploration");
        task5.setWhen("When time permits");
        task5.setWhere("Home and outdoor locations");
        task5.setWho("Self");
        task5.setWhy("Interested in picking up a creative hobby for personal fulfillment");
        task5.setEnds(LocalDateTime.now().plusDays(60));
        task5.setStatusUpdates(new TreeMap<>());
        task5.setImportance(Importance.OPTIONAL);

        // Bonus Task 6: Organize garage
        Task task6 = new Task();
        task6.setHow("Sort items into keep/donate/trash, install shelving, label boxes");
        task6.setWhat("Garage organization and decluttering");
        task6.setWhen("Next 3 weekends");
        task6.setWhere("Home garage");
        task6.setWho("Self and partner");
        task6.setWhy("Garage is cluttered and makes car parking difficult");
        task6.setEnds(LocalDateTime.now().plusDays(21));
        task6.setStatusUpdates(new TreeMap<>());
        task6.setImportance(Importance.NICE_TO_HAVE);

        // Bonus Task 7: Complete online course
        Task task7 = new Task();
        task7.setHow("Complete modules, solve coding exercises, build final project");
        task7.setWhat("Python programming course completion");
        task7.setWhen("5-7 hours per week");
        task7.setWhere("Home office");
        task7.setWho("Self");
        task7.setWhy("Want to learn programming for career advancement");
        task7.setEnds(LocalDateTime.now().plusDays(45));
        task7.setStatusUpdates(new TreeMap<>());
        task7.setImportance(Importance.IMPORTANT);

        tasks.addAll(List.of(task1, task2, task3, task4, task5, task6, task7));
        return tasks;
    }

    private List<Topic> mockRecurringTasks() {
        List<Topic> tasks = new ArrayList<>();// RecurringTask 1: Daily morning routine
        RecurringTask task1 = new RecurringTask();
        task1.setHow("Go for a 30-minute jog or gym session");
        task1.setWhat("Daily workout and fitness");
        task1.setWhen("Every weekday at 6:30 AM");
        task1.setWhere("Local park or home gym");
        task1.setWho("Self");
        task1.setWhy("Stay healthy and energized for the day");
        task1.setStatusUpdates(new TreeMap<>());
        task1.setImportance(Importance.IMPORTANT);

        // RecurringTask 2: Weekly grocery shopping
        RecurringTask task2 = new RecurringTask();
        task2.setHow("Make shopping list, visit supermarket, buy groceries");
        task2.setWhat("Weekly grocery and meal supplies");
        task2.setWhen("Every Saturday morning");
        task2.setWhere("Local supermarket");
        task2.setWho("Self");
        task2.setWhy("Stock up on fresh food and essentials for the week");
        task2.setStatusUpdates(new TreeMap<>());
        task2.setImportance(Importance.IMPORTANT);

        // RecurringTask 3: Bi-weekly meal planning
        RecurringTask task3 = new RecurringTask();
        task3.setHow("Plan weekly menus, prep ingredients, cook and freeze meals");
        task3.setWhat("Meal prep for the week");
        task3.setWhen("Every other Sunday evening");
        task3.setWhere("Home kitchen");
        task3.setWho("Self");
        task3.setWhy("Save time during the week and eat healthier");
        task3.setStatusUpdates(new TreeMap<>());
        task3.setImportance(Importance.NICE_TO_HAVE);

        // RecurringTask 4: Monthly bill payments
        RecurringTask task4 = new RecurringTask();
        task4.setHow("Review bills, process payments online, track expenses");
        task4.setWhat("Monthly bill payments and budget review");
        task4.setWhen("First day of each month");
        task4.setWhere("Home office");
        task4.setWho("Self");
        task4.setWhy("Stay on top of finances and avoid late payments");
        task4.setStatusUpdates(new TreeMap<>());
        task4.setImportance(Importance.CRITICAL);

        // RecurringTask 5: Quarterly personal finance review
        RecurringTask task5 = new RecurringTask();
        task5.setHow("Review bank statements, investments, savings goals");
        task5.setWhat("Quarterly financial review and planning");
        task5.setWhen("First week of Jan, Apr, Jul, Oct");
        task5.setWhere("Home office");
        task5.setWho("Self");
        task5.setWhy("Monitor financial health and adjust savings plan");
        task5.setStatusUpdates(new TreeMap<>());
        task5.setImportance(Importance.IMPORTANT);

        // RecurringTask 6: Weekly house cleaning
        RecurringTask task6 = new RecurringTask();
        task6.setHow("Vacuum, dust, clean bathrooms, change bed sheets");
        task6.setWhat("Weekly house maintenance");
        task6.setWhen("Every Saturday afternoon");
        task6.setWhere("Home");
        task6.setWho("Self");
        task6.setWhy("Maintain clean and organized living space");
        task6.setStatusUpdates(new TreeMap<>());
        task6.setImportance(Importance.NICE_TO_HAVE);

        // RecurringTask 7: Annual medical check-up
        RecurringTask task7 = new RecurringTask();
        task7.setHow("Schedule appointment, visit doctor, get lab work done");
        task7.setWhat("Yearly physical examination and health screening");
        task7.setWhen("March (during spring)");
        task7.setWhere("Local clinic or hospital");
        task7.setWho("Self");
        task7.setWhy("Monitor health and catch any issues early");
        task7.setStatusUpdates(new TreeMap<>());
        task7.setImportance(Importance.CRITICAL);

        // RecurringTask 8: Weekly laundry
        RecurringTask task8 = new RecurringTask();
        task8.setHow("Wash clothes, dry, fold, and put away");
        task8.setWhat("Weekly laundry and clothing maintenance");
        task8.setWhen("Every Wednesday evening");
        task8.setWhere("Home laundry room");
        task8.setWho("Self");
        task8.setWhy("Keep clothes clean and organized");
        task8.setStatusUpdates(new TreeMap<>());
        task8.setImportance(Importance.IMPORTANT);

        // RecurringTask 9: Monthly car maintenance
        RecurringTask task9 = new RecurringTask();
        task9.setHow("Check oil, tire pressure, fluid levels, clean interior");
        task9.setWhat("Monthly vehicle inspection");
        task9.setWhen("First Sunday of each month");
        task9.setWhere("Home driveway");
        task9.setWho("Self");
        task9.setWhy("Keep car running smoothly and extend lifespan");
        task9.setStatusUpdates(new TreeMap<>());
        task9.setImportance(Importance.NICE_TO_HAVE);

        tasks.addAll(List.of(task1, task2, task3, task4, task5, task6, task7, task8, task9));
        return tasks;
    }

    private List<Topic> mockProblems() {
        List<Topic> problems = new ArrayList<>();
        // Problem 1: Procrastination and time management
        Problem problem1 = new Problem();
        problem1.setHow("Break tasks into smaller chunks, use Pomodoro technique, set daily goals");
        problem1.setWhat("Procrastination and poor time management");
        problem1.setWhen("Ongoing, especially evenings and weekends");
        problem1.setWhere("Home office and personal workspace");
        problem1.setWho("Self");
        problem1.setWhy("Difficulty focusing on non-urgent tasks, leading to missed deadlines");
        problem1.setStatusUpdates(new TreeMap<>());
        problem1.setImportance(Importance.IMPORTANT);

        // Problem 2: Poor sleep schedule
        Problem problem2 = new Problem();
        problem2.setHow("Establish bedtime routine, limit screen time 1 hour before bed, keep room cool");
        problem2.setWhat("Sleep quality and consistency issues");
        problem2.setWhen("Every night, especially weekends");
        problem2.setWhere("Home bedroom");
        problem2.setWho("Self");
        problem2.setWhy("Irregular sleep is causing daytime fatigue and reduced productivity");
        problem2.setStatusUpdates(new TreeMap<>());
        problem2.setImportance(Importance.CRITICAL);

        // Problem 3: Budget management
        Problem problem3 = new Problem();
        problem3.setHow("Track expenses with budgeting app, set spending limits per category, review weekly");
        problem3.setWhat("Personal finance and budget management");
        problem3.setWhen("Weekly review and daily monitoring");
        problem3.setWhere("Home, banking apps, spreadsheets");
        problem3.setWho("Self");
        problem3.setWhy("Spending exceeds income, making it hard to save for goals");
        problem3.setStatusUpdates(new TreeMap<>());
        problem3.setImportance(Importance.CRITICAL);

        // Problem 4: Maintaining work-life balance
        Problem problem4 = new Problem();
        problem4.setHow("Set clear work hours, turn off notifications after 6 PM, plan weekend activities");
        problem4.setWhat("Work-life balance and burnout prevention");
        problem4.setWhen("Weekdays and weekends");
        problem4.setWhere("Home and workplace");
        problem4.setWho("Self and employer expectations");
        problem4.setWhy("Constantly checking work emails is causing stress and affecting family time");
        problem4.setStatusUpdates(new TreeMap<>());
        problem4.setImportance(Importance.IMPORTANT);

        // Problem 5: Social anxiety in group settings
        Problem problem5 = new Problem();
        problem5.setHow("Practice small talk, attend social events regularly, deep breathing exercises");
        problem5.setWhat("Social confidence and anxiety management");
        problem5.setWhen("When attending social events, networking, or group activities");
        problem5.setWhere("Social venues, workplace meetings, events");
        problem5.setWho("Self");
        problem5.setWhy("Fear of judgment prevents networking and building new relationships");
        problem5.setStatusUpdates(new TreeMap<>());
        problem5.setImportance(Importance.NICE_TO_HAVE);

        // Problem 6: Diet and nutrition habits
        Problem problem6 = new Problem();
        problem6.setHow("Meal prep on Sundays, keep healthy snacks available, reduce sugar intake");
        problem6.setWhat("Nutrition and dietary improvement");
        problem6.setWhen("Daily, with special attention during work hours");
        problem6.setWhere("Home kitchen, workplace");
        problem6.setWho("Self");
        problem6.setWhy("Poor diet is leading to weight gain and low energy levels");
        problem6.setStatusUpdates(new TreeMap<>());
        problem6.setImportance(Importance.IMPORTANT);

        // Problem 7: Digital distraction and focus
        Problem problem7 = new Problem();
        problem7.setHow(
                "Use app limiters, disable notifications, designate phone-free times, journal reasons for checking");
        problem7.setWhat("Digital wellness and focus management");
        problem7.setWhen("Throughout the day, especially during work and meals");
        problem7.setWhere("Home, workplace, public spaces");
        problem7.setWho("Self");
        problem7.setWhy("Excessive phone use is reducing focus, productivity, and in-person social quality");
        problem7.setStatusUpdates(new TreeMap<>());
        problem7.setImportance(Importance.NICE_TO_HAVE);

        // Problem 8: Relationship communication
        Problem problem8 = new Problem();
        problem8.setHow("Schedule weekly check-in talks, practice active listening, use 'I feel' statements");
        problem8.setWhat("Relationship communication and conflict resolution");
        problem8.setWhen("Weekly dedicated time, especially when issues arise");
        problem8.setWhere("Home, quiet comfortable space");
        problem8.setWho("Self and partner");
        problem8.setWhy("Unresolved concerns are building resentment and emotional distance");
        problem8.setStatusUpdates(new TreeMap<>());
        problem8.setImportance(Importance.CRITICAL);

        // Problem 9: Home organization and clutter
        Problem problem9 = new Problem();
        problem9.setHow("Declutter one room per week, establish storage systems, prevent new clutter");
        problem9.setWhat("Home organization and decluttering");
        problem9.setWhen("Weekends, 2-3 hours per session");
        problem9.setWhere("Home");
        problem9.setWho("Self");
        problem9.setWhy("Clutter is causing stress and making it hard to find things");
        problem9.setStatusUpdates(new TreeMap<>());
        problem9.setImportance(Importance.NICE_TO_HAVE);

        // Problem 10: Health anxiety
        Problem problem10 = new Problem();
        problem10.setHow("Schedule regular checkups, limit health research online, practice grounding techniques");
        problem10.setWhat("Health anxiety and stress management");
        problem10.setWhen("When symptoms appear or during stressful periods");
        problem10.setWhere("Home, medical offices");
        problem10.setWho("Self");
        problem10.setWhy("Constant worry is causing unnecessary stress and medical appointments");
        problem10.setStatusUpdates(new TreeMap<>());
        problem10.setImportance(Importance.OPTIONAL);

        problems.addAll(List.of(problem1, problem2, problem3, problem4, problem5, problem6, problem7, problem8,
                problem9, problem10));
        return problems;
    }
}
