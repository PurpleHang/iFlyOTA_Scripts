package com.iflytek.study.ota;


import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Logger {

    private static final String INFO_PREFIX = "INFO";
    private static final String WARN_PREFIX = "WARN";
    private static final String ERROR_PREFIX = "ERROR";
    private static final String DEBUG_PREFIX = "DEBUG";

    private static final SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");


    public static void info(String... messages) {
        Calendar calendar = Calendar.getInstance();
        String currentTime = formatter.format(calendar.getTime());
        String outputString = "".concat("[").concat(INFO_PREFIX).concat("/").concat(currentTime).concat("]").concat(" ");
        for (String message : messages) outputString = outputString.concat(message).concat(" ");
        System.out.println(outputString);
    }

    public static void warn(String... messages) {
        Calendar calendar = Calendar.getInstance();
        String currentTime = formatter.format(calendar.getTime());
        String outputString = "".concat("[").concat(WARN_PREFIX).concat("/").concat(currentTime).concat("]").concat(" ");
        for (String message : messages) outputString = outputString.concat(message).concat(" ");
        System.out.println(outputString);
    }

    public static void error(String... messages) {
        Calendar calendar = Calendar.getInstance();
        String currentTime = formatter.format(calendar.getTime());
        String outputString = "".concat("[").concat(ERROR_PREFIX).concat("/").concat(currentTime).concat("]").concat(" ");
        for (String message : messages) outputString = outputString.concat(message).concat(" ");
        System.out.println(outputString);
    }

    public static void debug(String... messages) {
        if(Main.isDebug) {
            Calendar calendar = Calendar.getInstance();
            String currentTime = formatter.format(calendar.getTime());
            String outputString = "".concat("[").concat(DEBUG_PREFIX).concat("/").concat(currentTime).concat("]").concat(" ");
            for (String message : messages) outputString = outputString.concat(message).concat(" ");
            System.out.println(outputString);
        }
    }
}
