package de.lifemytouch.ansi.core.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Log {

    private static final String RESET = "\u001B[0m";
    private static final String GREEN = "\u001B[32m";
    private static final String YELLOW = "\u001B[33m";
    private static final String RED = "\u001B[31m";
    private static final String BLUE = "\u001B[34m";
    private static final String GRAY = "\u001B[90m";

    private static final DateTimeFormatter FORMATTER =
            DateTimeFormatter.ofPattern("HH:mm:ss");

    private static void print(String level, String color, Object message) {
        String time = LocalDateTime.now().format(FORMATTER);

        String output = GRAY + "[" + time + "] " + RESET
                + color + "[" + level + "] " + RESET
                + message;

        if (level.equals("ERROR")) {
            System.err.println(output);
        } else {
            System.out.println(output);
        }
    }

    public static void info(Object message) {
        print("INFO", GREEN, message);
    }

    public static void warn(Object message) {
        print("WARN", YELLOW, message);
    }

    public static void error(Object message) {
        print("ERROR", RED, message);
    }

    public static void debug(Object message) {
        print("DEBUG", BLUE, message);
    }
}
