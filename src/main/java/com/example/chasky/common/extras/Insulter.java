package com.example.chasky.common.extras;

import java.util.Random;

public class Insulter {
    

    private static final Random R = new Random();

    private static final String[] COMMON = {
        "That's wrong.",
        "Invalid input. Try again.",
        "You typed that wrong.",
        "Nope.",
        "Wrong answer.",
        "That's not it.",
        "You're doing it wrong.",
        "Incorrect. Again."
    };

    private static final String[] UNCOMMON = {
        "Wow, really? That's invalid.",
        "Did you even read the prompt?",
        "That input is a crime against programming.",
        "I've seen smarter typos from a toddler.",
        "Your keyboard clearly has a personal vendetta against you.",
        "That's the kind of input that makes compilers cry."
    };

    private static final String[] RARE = {
        "You just unlocked the 'Certified Brain Damage' achievement.",
        "I've watched dial-up modems connect faster than your brain processes input.",
        "That input is so wrong it should be studied by physicists.",
        "You're the human equivalent of a stack overflow.",
        "Somewhere, a compiler just filed a restraining order against you."
    };

    // Weights: common 70%, uncommon 22%, rare 8%
    private static int pickTier() {
        int roll = R.nextInt(100);
        if (roll < 70) return 0;
        if (roll < 92) return 1;
        return 2;
    }

    public static void printInsult() {
        int tier = pickTier();
        switch (tier) {
            case 0: printCommon(); break;
            case 1: printUncommon(); break;
            case 2: printRare(); break;
        }
    }

    private static void printCommon() {
        System.out.println(COMMON[R.nextInt(COMMON.length)]);
    }

    private static void printUncommon() {
        String insult = UNCOMMON[R.nextInt(UNCOMMON.length)];
        // Yellow with a little flair
        System.out.println("\033[33m⚠ " + insult + "\033[0m");
    }

    private static void printRare() {
        String insult = RARE[R.nextInt(RARE.length)];
        // Rainbow border + typewriter effect
        String top = "╔" + "═".repeat(insult.length() + 2) + "╗";
        String mid = "║ " + insult + " ║";
        String bot = "╚" + "═".repeat(insult.length() + 2) + "╝";

        System.out.println(rainbow(top));
        typeWriter(mid, 30); // 30ms per char
        System.out.println(rainbow(bot));
    }

    private static String rainbow(String s) {
        int[] colors = {31, 32, 33, 34, 35, 36};
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            sb.append("\033[").append(colors[i % colors.length]).append("m")
              .append(s.charAt(i));
        }
        sb.append("\033[0m");
        return sb.toString();
    }

    private static void typeWriter(String s, int delay) {
        for (char c : s.toCharArray()) {
            System.out.print(c);
            System.out.flush();
            try { Thread.sleep(delay); } catch (InterruptedException e) { Thread.currentThread().interrupt(); }
        }
        System.out.println();
    }
}
