package com.example.chasky.llm;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class AnimateSlide {
    private List<String> outputBuffer = new ArrayList<>();
    private StringBuilder currentLine = new StringBuilder();
    private PrintStream originalOut;

    public void setupOutputCapture() {
        originalOut = System.out;

        System.setOut(new PrintStream(originalOut) {

            @Override
            public void print(String s) {
                originalOut.print(s);
                appendToLine(s);
            }

            @Override
            public void print(Object obj) {
                originalOut.print(obj);
                appendToLine(String.valueOf(obj));
            }

            @Override
            public void print(char c) {
                originalOut.print(c);
                appendToLine(String.valueOf(c));
            }

            @Override
            public void println() {
                originalOut.println();
                commitLine();
            }

            @Override
            public void println(String s) {
                originalOut.println(s);
                appendToLine(s);
                commitLine();
            }

            @Override
            public void println(Object obj) {
                originalOut.println(obj);
                appendToLine(String.valueOf(obj));
                commitLine();
            }

            @Override
            public void println(char c) {
                originalOut.println(c);
                appendToLine(String.valueOf(c));
                commitLine();
            }

            @Override
            public PrintStream printf(Locale l, String format, Object... args) {
                originalOut.printf(l, format, args);
                appendToLine(String.format(l, format, args));
                return this;
            }

            @Override
            public PrintStream format(Locale l, String format, Object... args) {
                return printf(l, format, args);
            }
        });
    }

    private void appendToLine(String text) {
        // Handle embedded newlines (e.g. from a string containing \n)
        for (String part : text.split("\n", -1)) {
            if (part.isEmpty() && currentLine.length() == 0) {
                commitLine(); // blank line
            } else {
                currentLine.append(part);
                if (text.endsWith("\n") || part != text) {
                    // newline was in the middle — commit
                    // (simplified: just commit on the last part if it was split)
                }
            }
        }
    }

    private void commitLine() {
        outputBuffer.add(currentLine.toString());
        currentLine = new StringBuilder();
    }

    private List<String> fromSlide;
    private List<String> toSlide;

    public void fromSlide() {
        fromSlide = List.copyOf(outputBuffer);
        outputBuffer.clear();
    }

    public void toSlide() {
        toSlide = List.copyOf(outputBuffer);
        outputBuffer.clear();
    }

    public void slideRight() {
        if (fromSlide == null || toSlide == null)
            return;

        int cols = 180;
        int rows = Math.max(fromSlide.size(), toSlide.size());

        // Pad both to same row count and width
        String[] from = padFrame(fromSlide, rows, cols);
        String[] to = padFrame(toSlide, rows, cols);

        System.out.print("\033[?25l"); // hide cursor

        try {
            long frameDelay = 1000 / 60; // 60 FPS
            int step = Math.max(1, cols / 30); // finish in ~30 frames

            for (int i = 0; i < cols; i += step) {
                StringBuilder sb = new StringBuilder("\033[H");
                for (int r = 0; r < rows; r++) {
                    if (r > 0)
                        sb.append('\n');
                    sb.append(from[r].substring(i))
                            .append(to[r].substring(0, i));
                }
                System.out.print(sb);
                System.out.flush();
                Thread.sleep(frameDelay);
            }

            // Final frame
            StringBuilder sb = new StringBuilder("\033[H");
            for (int r = 0; r < rows; r++) {
                if (r > 0)
                    sb.append('\n');
                sb.append(to[r]);
            }
            System.out.print(sb);
            System.out.flush();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } finally {
            System.out.print("\033[?25h"); // show cursor
        }
        System.out.print("\033[1G\n");
    }

    public void slideRightReverse() {
        if (fromSlide == null || toSlide == null)
            return;

        int cols = 180;
        int rows = Math.max(fromSlide.size(), toSlide.size());

        // Pad both to same row count and width
        String[] from = padFrame(toSlide, rows, cols);
        String[] to = padFrame(fromSlide, rows, cols);

        System.out.print("\033[?25l"); // hide cursor

        try {
            long frameDelay = 1000 / 60; // 60 FPS
            int step = Math.max(1, cols / 30); // finish in ~30 frames

            for (int i = 0; i < cols; i += step) {
                StringBuilder sb = new StringBuilder("\033[H");
                for (int r = 0; r < rows; r++) {
                    if (r > 0)
                        sb.append('\n');
                    sb.append(from[r].substring(i))
                            .append(to[r].substring(0, i));
                }
                System.out.print(sb);
                System.out.flush();
                Thread.sleep(frameDelay);
            }

            // Final frame
            StringBuilder sb = new StringBuilder("\033[H");
            for (int r = 0; r < rows; r++) {
                if (r > 0)
                    sb.append('\n');
                sb.append(to[r]);
            }
            System.out.print(sb);
            System.out.flush();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } finally {
            System.out.print("\033[?25h"); // show cursor
        }
        System.out.print("\033[1G\n");
    }

    private String[] padFrame(List<String> rows, int rowCount, int width) {
        String[] result = new String[rowCount];
        int offset = rowCount - rows.size();
        for (int i = 0; i < rowCount; i++) {
            String row = (i >= offset) ? rows.get(i - offset) : "";
            if (row.length() >= width) {
                result[i] = row.substring(0, width);
            } else {
                result[i] = row + " ".repeat(width - row.length());
            }
        }
        return result;
    }

}
