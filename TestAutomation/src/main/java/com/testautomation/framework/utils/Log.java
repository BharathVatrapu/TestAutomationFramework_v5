package com.testautomation.framework.utils;

import org.apache.log4j.Logger;

public class Log {

    //Initialize Log4j instance
    private static Logger Log = Logger.getLogger(Log.class.getName());

    //We can use it when starting tests
    public static void startLog (String message){
        Log.info("------------------------------");
        Log.info("Mehtod# "+message+" :: is Start");
    }

    //We can use it when ending tests
    public static void endLog (String message){
        Log.info("Mehtod# "+message + " :: is End");
        Log.info("------------------------------");
    }

    //Info Level Logs
    public static void info (String message) {
        Log.info(message);
    }

    //Warn Level Logs
    public static void warn (String message) {
        Log.warn(message);
    }

    //Error Level Logs
    public static void error (String message) {
        Log.error(message);
    }

    //Fatal Level Logs
    public static void fatal (String message) {
        Log.fatal(message);
    }

    //Debug Level Logs
    public static void debug (String message) {
        Log.debug(message);
    }
}
