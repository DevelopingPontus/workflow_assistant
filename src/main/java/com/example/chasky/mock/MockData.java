package com.example.chasky.mock;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

import com.example.chasky.common.enums.Importance;
import com.example.chasky.features.topic.Topic;
import com.example.chasky.features.topic.implementations.Problem;
import com.example.chasky.features.topic.implementations.RecurringTask;
import com.example.chasky.features.topic.implementations.Task;
import com.example.chasky.features.user.User;

public class MockData {

        public List<User> mockData(List<User> users) {
                User b = new User("batman.gotham@encrypted.net");
                User j = new User("why.so.serious.batman@pranks4u.com");
                users.add(b);
                users.add(j);

                List<Topic> userTopics = new ArrayList<>();
                userTopics.addAll(mockTasksBatMan());
                userTopics.addAll(mockRecurringTasksBatMan());
                userTopics.addAll(mockProblems());
                b.setTopics(userTopics);

                userTopics = new ArrayList<>();
                userTopics.addAll(mockTasksJoker());
                userTopics.addAll(mockRecurringTasksJoker());
                userTopics.addAll(mockProblems());
                j.setTopics(userTopics);
                return users;
        }

        private List<Topic> mockTasksJoker() {
                List<Topic> tasks = new ArrayList<>();
                // Task 1: Host "World's Best Joke" Comedy Night
                Task jokerTask1 = new Task();
                jokerTask1.setWhat("Organize elaborate stand-up comedy show at abandoned theater");
                jokerTask1.setHow(
                                "Write 500+ jokes, book stage lighting, create custom microphone shaped like a rubber chicken, design audience seating with whoopee cushions, bake exploding cupcakes for intermission");
                jokerTask1.setWhen("This Saturday at 8:00 PM");
                jokerTask1.setWhere("Gotham Theatre of Broken Dreams");
                jokerTask1.setWho("Joker, Harley Quinn, invited goons, Batman (surprise guest!)");
                jokerTask1.setWhy(
                                "Prove comedy is superior to violence (narrator: it isn't), make Batman laugh involuntarily");
                jokerTask1.setEnds(LocalDateTime.now().plusDays(5));
                jokerTask1.setImportance(Importance.CRITICAL);

                TreeMap<LocalDateTime, String> jUpdates1 = new TreeMap<>();
                jUpdates1.put(LocalDateTime.now().minusHours(72),
                                "STARTED - Wrote first 200 jokes. Why did the bat cross the road? To confuse Batman!");
                jUpdates1.put(LocalDateTime.now().minusHours(60),
                                "IN PROGRESS - Ordered rubber chicken microphone. It quacks when I yell. PERFECT!");
                jUpdates1.put(LocalDateTime.now().minusHours(48),
                                "IN PROGRESS - Installing whoopee cushions under every seat. Comedy gold!");
                jUpdates1.put(LocalDateTime.now().minusHours(36),
                                "IN PROGRESS - Baked 300 cupcakes. Only 50 actually explode. We'll see what happens!");
                jUpdates1.put(LocalDateTime.now().minusHours(24),
                                "IN PROGRESS - Designed confetti cannon that shoots only pictures of Batman's face. Genius!");
                jUpdates1.put(LocalDateTime.now().minusHours(12),
                                "IN PROGRESS - Sent Batman formal invitation written in glitter and dad jokes. Can't refuse now!");
                jokerTask1.setStatusUpdates(jUpdates1);

                // Task 2: Paint Every Hydrant in Gotham Purple
                Task jokerTask2 = new Task();
                jokerTask2.setWhat("Repaint all 2,847 fire hydrants in Gotham City purple with silly faces");
                jokerTask2.setHow(
                                "Acquire industrial paint supplies, hire goons with paintbrushes, draw googly eyes and mustaches on each hydrant, leave confusing notes signed 'The Hydrant Jester'");
                jokerTask2.setWhen("Over next 10 days");
                jokerTask2.setWhere("Every street in Gotham City");
                jokerTask2.setWho("Joker, Harley Quinn, 30 goons with paintbrushes, very confused fire department");
                jokerTask2.setWhy(
                                "Transform Gotham into purple absurdity; make firefighters question their life choices");
                jokerTask2.setEnds(LocalDateTime.now().plusDays(10));
                jokerTask2.setImportance(Importance.IMPORTANT);

                TreeMap<LocalDateTime, String> jUpdates2 = new TreeMap<>();
                jUpdates2.put(LocalDateTime.now().minusHours(168),
                                "STARTED - Purchased 5,000 gallons of purple paint and googly eye stickers. BUDGET EXCEEDED!");
                jUpdates2.put(LocalDateTime.now().minusHours(144),
                                "IN PROGRESS - Painted hydrants 1-500. Drawing mustaches is harder than expected.");
                jUpdates2.put(LocalDateTime.now().minusHours(120),
                                "IN PROGRESS - Harley named her favorite hydrant 'Sir Squirtsworth III'. She's attached.");
                jUpdates2.put(LocalDateTime.now().minusHours(96),
                                "IN PROGRESS - Fire Chief just noticed. He looks confused. Mission success!");
                jUpdates2.put(LocalDateTime.now().minusHours(72),
                                "IN PROGRESS - One goon painted his own face purple by accident. Hired him full-time!");
                jokerTask2.setStatusUpdates(jUpdates2);

                // Task 3: Create Massive Rubber Duck Army
                Task jokerTask3 = new Task();
                jokerTask3.setWhat("Build army of 10,000 giant inflatable rubber ducks wearing tiny tuxedos");
                jokerTask3.setHow(
                                "Order bulk rubber ducks, design miniature formal wear, recruit seamstress goons, inflate ducks strategically around Gotham Harbor, add squeaky sound effects");
                jokerTask3.setWhen("Complete within 2 weeks");
                jokerTask3.setWhere("Secret warehouse / Gotham Harbor");
                jokerTask3.setWho("Joker, Harley Quinn, fashion-conscious henchmen");
                jokerTask3.setWhy(
                                "Confuse Batman with the sheer pointlessness; enjoy the squeaking sound; maybe use them as rafts?");
                jokerTask3.setEnds(LocalDateTime.now().plusDays(14));
                jokerTask3.setImportance(Importance.IMPORTANT);

                TreeMap<LocalDateTime, String> jUpdates3 = new TreeMap<>();
                jUpdates3.put(LocalDateTime.now().minusHours(120),
                                "STARTED - Ordered 10,000 ducks from online retailer. Delivery guy looked concerned.");
                jUpdates3.put(LocalDateTime.now().minusHours(96),
                                "IN PROGRESS - Seamstress finished first tuxedo. It's adorable. I'm obsessed!");
                jUpdates3.put(LocalDateTime.now().minusHours(72),
                                "IN PROGRESS - Inflated 500 ducks. Warehouse smells like rubber and regret.");
                jUpdates3.put(LocalDateTime.now().minusHours(48),
                                "IN PROGRESS - Harley accidentally sat on a fully inflated duck. QUACK EXPLOSION!");
                jUpdates3.put(LocalDateTime.now().minusHours(24),
                                "IN PROGRESS - Added squeaky sound effects. Too loud. Neighbors complaining. Worth it!");
                jokerTask3.setStatusUpdates(jUpdates3);

                // Task 4: Write Autobiography in Limerick Form
                Task jokerTask4 = new Task();
                jokerTask4.setWhat("Write 500-page autobiography entirely in limericks");
                jokerTask4.setHow(
                                "Reflect on childhood trauma but make it rhyme, include embarrassing anecdotes, add illustrations of self, publish through underground press");
                jokerTask4.setWhen("Complete by end of month");
                jokerTask4.setWhere("Hideout library/writing desk");
                jokerTask4.setWho("Joker, Harley Quinn (editor)");
                jokerTask4.setWhy("Create literary masterpiece; make Batman jealous of his writing talent");
                jokerTask4.setEnds(LocalDateTime.now().plusDays(15));
                jokerTask4.setImportance(Importance.IMPORTANT);

                TreeMap<LocalDateTime, String> jUpdates4 = new TreeMap<>();
                jUpdates4.put(LocalDateTime.now().minusHours(144),
                                "STARTED - Wrote opening limerick. 'There once was a clown feeling blue...' GENIUS!");
                jUpdates4.put(LocalDateTime.now().minusHours(120),
                                "IN PROGRESS - Chapter 2 complete! 47 limericks about my first prank. Harley laughed-cried.");
                jUpdates4.put(LocalDateTime.now().minusHours(96),
                                "IN PROGRESS - Struggling with meter. 'Sardonic' doesn't rhyme with anything. ARGH!");
                jUpdates4.put(LocalDateTime.now().minusHours(72),
                                "IN PROGRESS - Harley suggested I just rhyme 'laugh' with 'bath'. She might be the real writer here.");
                jUpdates4.put(LocalDateTime.now().minusHours(48),
                                "IN PROGRESS - 300 limericks done. My hand hurts. Worth it for art!");
                jokerTask4.setStatusUpdates(jUpdates4);

                tasks.addAll(List.of(jokerTask1, jokerTask2, jokerTask3, jokerTask4));
                return tasks;
        }

        private List<Topic> mockTasksBatMan() {
                List<Topic> tasks = new ArrayList<>();// Task 1: Investigate Joker's Hideout
                Task batmanTask1 = new Task();
                batmanTask1.setWhat("Investigate Joker's suspected hideout in abandoned Amusement Park");
                batmanTask1.setHow(
                                "Deploy surveillance drones, gather forensic evidence, analyze structural blueprints, interview informants");
                batmanTask1.setWhen("Within 3 days before criminals relocate");
                batmanTask1.setWhere("Abandoned Gotham Amusement Park / Batcave forensics lab");
                batmanTask1.setWho("Batman, Oracle, Robin");
                batmanTask1.setWhy("Prevent planned terrorist attack on Gotham City Hall");
                batmanTask1.setEnds(LocalDateTime.now().plusDays(2));
                batmanTask1.setImportance(Importance.CRITICAL);

                TreeMap<LocalDateTime, String> updates1 = new TreeMap<>();
                updates1.put(LocalDateTime.now().minusHours(48),
                                "STARTED - Oracle identified location via thermal imaging. Sent drone reconnaissance.");
                updates1.put(LocalDateTime.now().minusHours(36),
                                "IN PROGRESS - Drones detected armed guards and explosive devices. Gathered floor plans.");
                updates1.put(LocalDateTime.now().minusHours(24),
                                "IN PROGRESS - Analyzed evidence with Robin. Found Joker's plans targeting City Hall gala.");
                updates1.put(LocalDateTime.now().minusHours(12),
                                "IN PROGRESS - Coordinated with Oracle to disable security systems remotely.");
                updates1.put(LocalDateTime.now().minusHours(4),
                                "NEARLY COMPLETE - Briefed Robin on infiltration strategy. Ready for tonight's operation.");
                batmanTask1.setStatusUpdates(updates1);

                // Task 2: Train Robin
                Task batmanTask2 = new Task();
                batmanTask2.setWhat("Advanced combat and detective skills training for Robin");
                batmanTask2.setHow(
                                "Martial arts drills, detective case studies, simulation exercises, real-world patrol experience");
                batmanTask2.setWhen("3 times per week, ongoing");
                batmanTask2.setWhere("Batcave training room");
                batmanTask2.setWho("Batman, Robin");
                batmanTask2.setWhy("Prepare Robin for leadership role and solo missions");
                batmanTask2.setEnds(LocalDateTime.now().plusDays(7));
                batmanTask2.setImportance(Importance.IMPORTANT);

                TreeMap<LocalDateTime, String> updates2 = new TreeMap<>();
                updates2.put(LocalDateTime.now().minusHours(72),
                                "STARTED - Robin completed 50 combat simulations. Improved 15% on reaction time.");
                updates2.put(LocalDateTime.now().minusHours(48),
                                "IN PROGRESS - Detective case study: The Riddler's pattern analysis. 9/10 cases solved.");
                updates2.put(LocalDateTime.now().minusHours(24),
                                "IN PROGRESS - Night patrol with supervised reconnaissance of Narrows district.");
                updates2.put(LocalDateTime.now().minusHours(6),
                                "IN PROGRESS - Advanced grappling techniques session. Demonstrating mastery of new moves.");
                batmanTask2.setStatusUpdates(updates2);

                // Task 3: Repair Batmobile
                Task batmanTask3 = new Task();
                batmanTask3.setWhat("Repair Batmobile engine and armor plating damage");
                batmanTask3.setHow(
                                "Replace damaged turbine, reseal armor panels, recalibrate targeting system, test drive");
                batmanTask3.setWhen("Before next week's stakeout operations");
                batmanTask3.setWhere("Batcave garage");
                batmanTask3.setWho("Batman, Lucius Fox");
                batmanTask3.setWhy("Vehicle inoperable after collision with Penguin's forces");
                batmanTask3.setEnds(LocalDateTime.now().plusDays(4));
                batmanTask3.setImportance(Importance.CRITICAL);

                TreeMap<LocalDateTime, String> updates3 = new TreeMap<>();
                updates3.put(LocalDateTime.now().minusHours(60),
                                "STARTED - Lucius Fox ordered replacement turbine from Applied Sciences. Arrived.");
                updates3.put(LocalDateTime.now().minusHours(48),
                                "IN PROGRESS - Removed damaged turbine and inspected engine block for secondary damage.");
                updates3.put(LocalDateTime.now().minusHours(36),
                                "IN PROGRESS - Installed new turbine. Resealed 60% of armor panels.");
                updates3.put(LocalDateTime.now().minusHours(18),
                                "IN PROGRESS - Completed armor repairs. Recalibrating targeting system now.");
                updates3.put(LocalDateTime.now().minusHours(4),
                                "NEARLY COMPLETE - Test drive scheduled for tomorrow. All systems nominal.");
                batmanTask3.setStatusUpdates(updates3);

                // Task 4: Intelligence Gathering on Penguin
                Task batmanTask4 = new Task();
                batmanTask4.setWhat("Gather intelligence on Penguin's weapons smuggling operation");
                batmanTask4.setHow(
                                "Bug Iceberg Lounge, track shipping containers, monitor dark web communications, shadow Penguin's associates");
                batmanTask4.setWhen("Next 10 days");
                batmanTask4.setWhere("Iceberg Lounge / Gotham Harbor / Batcave");
                batmanTask4.setWho("Batman, Catwoman (informant)");
                batmanTask4.setWhy("Prevent illegal weapons from reaching gang leaders");
                batmanTask4.setEnds(LocalDateTime.now().plusDays(10));
                batmanTask4.setImportance(Importance.IMPORTANT);

                TreeMap<LocalDateTime, String> updates4 = new TreeMap<>();
                updates4.put(LocalDateTime.now().minusHours(120),
                                "STARTED - Catwoman confirmed Penguin meeting new arms dealer. Obtained warehouse address.");
                updates4.put(LocalDateTime.now().minusHours(96),
                                "IN PROGRESS - Deployed listening devices in Iceberg Lounge. Captured encrypted communications.");
                updates4.put(LocalDateTime.now().minusHours(72),
                                "IN PROGRESS - Oracle decrypted messages. Shipment arriving at Gotham Harbor tonight.");
                updates4.put(LocalDateTime.now().minusHours(48),
                                "IN PROGRESS - Tracked 3 cargo containers to abandoned warehouse on 52nd Street.");
                updates4.put(LocalDateTime.now().minusHours(24),
                                "IN PROGRESS - Photographed weapons inventory. Identified military-grade ordnance.");
                batmanTask4.setStatusUpdates(updates4);

                tasks.addAll(List.of(batmanTask1, batmanTask2, batmanTask3, batmanTask4));
                return tasks;
        }

        private List<Topic> mockRecurringTasksJoker() {
                List<Topic> tasks = new ArrayList<>();

                // Recurring Task 1: Daily Knock-Knock Joke to Batman
                RecurringTask jokerRecurring1 = new RecurringTask();
                jokerRecurring1.setWhat("Leave increasingly terrible knock-knock jokes at crime scenes");
                jokerRecurring1.setHow(
                                "Write joke on card, leave at scene, wait for Batman's groan, film his reaction, edit into compilation video");
                jokerRecurring1.setWhen("Every single day at noon");
                jokerRecurring1.setWhere("Random Gotham locations");
                jokerRecurring1.setWho("Joker, Harley Quinn (cameraman)");
                jokerRecurring1.setWhy("Weaponize dad jokes; break Batman's will through comedy");
                jokerRecurring1.setImportance(Importance.CRITICAL);
                jokerRecurring1.setStatusUpdates(new TreeMap<>());

                // Recurring Task 2: Competitive Baking with Harley
                RecurringTask jokerRecurring2 = new RecurringTask();
                jokerRecurring2.setWhat("Host weekly baking competitions with Harley Quinn");
                jokerRecurring2.setHow(
                                "Choose theme (chocolate chaos, strawberry surprise, mystery ingredient), set 2-hour timer, judge harshly, eat results, laugh about mishaps");
                jokerRecurring2.setWhen("Every Wednesday at 3:00 PM");
                jokerRecurring2.setWhere("Hideout kitchen");
                jokerRecurring2.setWho("Joker, Harley Quinn");
                jokerRecurring2.setWhy("Domestic chaos is best chaos; destroy kitchen creatively");
                jokerRecurring2.setImportance(Importance.IMPORTANT);
                jokerRecurring2.setStatusUpdates(new TreeMap<>());

                // Recurring Task 3: Collect Vintage Rubber Ducks
                RecurringTask jokerRecurring3 = new RecurringTask();
                jokerRecurring3.setWhat("Scout Gotham for rare and vintage rubber ducks");
                jokerRecurring3.setHow(
                                "Visit antique shops, outbid collectors on eBay, trade with other hoarders, catalogue entire collection by era and squeak pitch");
                jokerRecurring3.setWhen("Every Saturday morning");
                jokerRecurring3.setWhere("Antique shops, toy stores, eBay");
                jokerRecurring3.setWho("Joker, increasingly obsessed");
                jokerRecurring3.setWhy("Building world's premier duck collection; it's the little things");
                jokerRecurring3.setImportance(Importance.IMPORTANT);
                jokerRecurring3.setStatusUpdates(new TreeMap<>());

                // Recurring Task 4: Practice Balloon Animal Artistry
                RecurringTask jokerRecurring4 = new RecurringTask();
                jokerRecurring4.setWhat("Perfect balloon animal creations (balloon swords, poodles, elaborate hats)");
                jokerRecurring4.setHow(
                                "Practice twisting techniques, experiment with colored balloons, create increasingly absurd shapes, gift random civilians on street");
                jokerRecurring4.setWhen("Every Thursday evening");
                jokerRecurring4.setWhere("Hideout living room");
                jokerRecurring4.setWho("Joker");
                jokerRecurring4.setWhy(
                                "Master unexpected skill; confuse Batman with wholesome balloon animals between crimes");
                jokerRecurring4.setImportance(Importance.IMPORTANT);
                jokerRecurring4.setStatusUpdates(new TreeMap<>());

                // Recurring Task 5: Maintain Elaborate Costume Wardrobe
                RecurringTask jokerRecurring5 = new RecurringTask();
                jokerRecurring5.setWhat("Rotate between 47 different Joker outfit variations with themed accessories");
                jokerRecurring5.setHow(
                                "Purple suit Mondays, polka-dot suspenders Wednesdays, oversized bow-tie Fridays, seasonal themes, maintain pristine condition");
                jokerRecurring5.setWhen("Every Monday morning styling session");
                jokerRecurring5.setWhere("Hideout wardrobe closet");
                jokerRecurring5.setWho("Joker, reluctant Harley Quinn fashion consultant");
                jokerRecurring5.setWhy("Never let Batman see the same outfit twice; fashion is chaos");
                jokerRecurring5.setImportance(Importance.IMPORTANT);
                jokerRecurring5.setStatusUpdates(new TreeMap<>());

                // Recurring Task 6: Compile Batman Blooper Reel
                RecurringTask jokerRecurring6 = new RecurringTask();
                jokerRecurring6.setWhat("Film and edit moments of Batman being silly/tripping/getting stuck");
                jokerRecurring6.setHow(
                                "Deploy hidden cameras, record audio, add funny sound effects, compile montages, share anonymously online");
                jokerRecurring6.setWhen("Ongoing whenever opportunity strikes");
                jokerRecurring6.setWhere("All Gotham City");
                jokerRecurring6.setWho("Joker, Harley Quinn (executive producer)");
                jokerRecurring6.setWhy(
                                "Humanize the Dark Knight through embarrassing video compilations; break his mystique");
                jokerRecurring6.setImportance(Importance.CRITICAL);
                jokerRecurring6.setStatusUpdates(new TreeMap<>());

                tasks.addAll(List.of(
                                jokerRecurring1,
                                jokerRecurring2,
                                jokerRecurring3,
                                jokerRecurring4,
                                jokerRecurring5,
                                jokerRecurring6));
                return tasks;
        }

        private List<Topic> mockRecurringTasksBatMan() {
                List<Topic> tasks = new ArrayList<>();// RecurringTask 1: Daily morning routine// Recurring Task 1:
                                                      // Nightly
                                                      // Patrol
                RecurringTask batmanRecurring1 = new RecurringTask();
                batmanRecurring1.setWhat("Nightly patrol of Gotham City streets and rooftops");
                batmanRecurring1.setHow(
                                "Grapple-gun traversal, surveillance of crime hotspots, respond to emergency calls, interrogate suspects");
                batmanRecurring1.setWhen("Every night at 9:00 PM");
                batmanRecurring1.setWhere("Gotham City rooftops, streets, and alleyways");
                batmanRecurring1.setWho("Batman, sometimes Robin or Batgirl");
                batmanRecurring1.setWhy("Deter criminal activity and protect Gotham citizens");
                batmanRecurring1.setImportance(Importance.CRITICAL);
                batmanRecurring1.setStatusUpdates(new TreeMap<>());

                // Recurring Task 2: Morning Combat Training
                RecurringTask batmanRecurring2 = new RecurringTask();
                batmanRecurring2.setWhat("Advanced combat training and martial arts practice");
                batmanRecurring2.setHow(
                                "Sparring with training drones, punching bags, agility courses, weapon drills, choreography of new techniques");
                batmanRecurring2.setWhen("Every morning at 5:00 AM");
                batmanRecurring2.setWhere("Batcave training room");
                batmanRecurring2.setWho("Batman");
                batmanRecurring2.setWhy("Maintain peak physical condition and combat effectiveness");
                batmanRecurring2.setImportance(Importance.IMPORTANT);
                batmanRecurring2.setStatusUpdates(new TreeMap<>());

                // Recurring Task 3: Intelligence Briefing with Oracle
                RecurringTask batmanRecurring3 = new RecurringTask();
                batmanRecurring3.setWhat("Daily intelligence briefing and crime analysis");
                batmanRecurring3.setHow(
                                "Review overnight crime reports, analyze gang activity patterns, check surveillance feeds, discuss new leads");
                batmanRecurring3.setWhen("Every morning at 7:30 AM");
                batmanRecurring3.setWhere("Batcave operations center");
                batmanRecurring3.setWho("Batman, Oracle");
                batmanRecurring3.setWhy("Stay informed on criminal activity and coordinate strategy");
                batmanRecurring3.setImportance(Importance.IMPORTANT);
                batmanRecurring3.setStatusUpdates(new TreeMap<>());

                // Recurring Task 4: Batmobile Maintenance Check
                RecurringTask batmanRecurring4 = new RecurringTask();
                batmanRecurring4.setWhat("Weekly Batmobile systems check and maintenance");
                batmanRecurring4.setHow(
                                "Engine diagnostics, armor inspection, weapons system verification, tire rotation, fuel and coolant check");
                batmanRecurring4.setWhen("Every Sunday at 2:00 PM");
                batmanRecurring4.setWhere("Batcave garage");
                batmanRecurring4.setWho("Batman, Lucius Fox");
                batmanRecurring4.setWhy("Ensure vehicle is operational and ready for emergency response");
                batmanRecurring4.setImportance(Importance.IMPORTANT);
                batmanRecurring4.setStatusUpdates(new TreeMap<>());

                // Recurring Task 5: Network Security Audit
                RecurringTask batmanRecurring5 = new RecurringTask();
                batmanRecurring5.setWhat("Batcave network security audit and system updates");
                batmanRecurring5.setHow(
                                "Check firewall logs, update encryption protocols, scan for intrusions, backup critical data, patch vulnerabilities");
                batmanRecurring5.setWhen("Every Tuesday at 10:00 PM");
                batmanRecurring5.setWhere("Batcave computer lab");
                batmanRecurring5.setWho("Batman, Oracle");
                batmanRecurring5.setWhy(
                                "Protect classified information and prevent hackers from infiltrating operations");
                batmanRecurring5.setImportance(Importance.CRITICAL);
                batmanRecurring5.setStatusUpdates(new TreeMap<>());

                // Recurring Task 6: Investigate Gotham Police Department Tips
                RecurringTask batmanRecurring6 = new RecurringTask();
                batmanRecurring6.setWhat("Follow up on anonymous crime tips via police department");
                batmanRecurring6.setHow(
                                "Review tip hotline, prioritize leads, conduct field investigation, gather evidence");
                batmanRecurring6.setWhen("Every weekday at 3:00 PM");
                batmanRecurring6.setWhere("Police department rooftop / Crime scenes");
                batmanRecurring6.setWho("Batman, Commissioner Gordon");
                batmanRecurring6.setWhy("Coordinate with GCPD and respond to credible criminal threats");
                batmanRecurring6.setImportance(Importance.IMPORTANT);
                batmanRecurring6.setStatusUpdates(new TreeMap<>());

                // Recurring Task 7: Suit Maintenance and Upgrade
                RecurringTask batmanRecurring7 = new RecurringTask();
                batmanRecurring7.setWhat("Batsuit cleaning, repairs, and technology upgrades");
                batmanRecurring7.setHow(
                                "Clean armor, patch tears, test communication systems, upgrade body armor plating, check gadget batteries");
                batmanRecurring7.setWhen("Every Friday at 8:00 PM");
                batmanRecurring7.setWhere("Batcave suit room");
                batmanRecurring7.setWho("Batman, Lucius Fox");
                batmanRecurring7.setWhy("Keep suit functional and protect against injuries during missions");
                batmanRecurring7.setImportance(Importance.IMPORTANT);
                batmanRecurring7.setStatusUpdates(new TreeMap<>());

                tasks.addAll(List.of(batmanRecurring1, batmanRecurring2, batmanRecurring3, batmanRecurring4,
                                batmanRecurring5,
                                batmanRecurring6, batmanRecurring7));
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
                problem10.setHow(
                                "Schedule regular checkups, limit health research online, practice grounding techniques");
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
