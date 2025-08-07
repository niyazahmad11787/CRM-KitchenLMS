package com.qa.hippo.main.utilities;

import com.aventstack.extentreports.ExtentTest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * This internally uses Log4j api to log the messages.
 *
 * @author HTPL QA Team
 *
 */
public class HTPLLogger {
    private static Logger logger;
    private static ExtentTest extentTest;

    public static void setLogger(Class<?> clazz, ExtentTest test) {
        logger = LogManager.getLogger(clazz);
        extentTest = test;
    }
    private static Logger log = LogManager.getLogger(HTPLLogger.class.getName());
    /**
     * Default constructor.
     */
    private HTPLLogger() {
    }

    /**
     * Set the Class for logger.
     *
     * @param classObj Class Instance of test class
     */
    public static void setClass(Object classObj) {
        log = LogManager.getLogger(classObj.getClass().getName());
    }

    /**
     * This method logs message as info..
     *
     * @param message String message to log.
     */
    public static void info(String message) {
        log.info(message);
    }

    /**
     * This is overloaded method to log message with exception..
     *
     * @param message String message to log.
     */
    public static void info(String message, Throwable e) {
        log.info(message, e);
    }

    /**
     * This method warn message as info.
     *
     * @param message String message to log.
     */
    public static void warn(String message) {
        log.warn(message);
    }

    /**
     * This is overloaded method to log message with exception..
     *
     * @param message String message to log.
     */
    public static void warn(String message, Throwable e) {
        log.warn(message, e);
    }

    /**
     * This method warn message as error.
     *
     * @param message String message to log.
     */
    public static void error(String message) {
        log.error(message);
    }

    /**
     * This is overloaded method to log message with exception..
     *
     * @param message String message to log.
     */
    public static void error(String message, Throwable e) {
        log.error(message, e);
    }

    /**
     * This method warn message as debug.
     *
     * @param message String message to log.
     */
    public static void debug(String message) {
        log.debug(message);
    }

    /**
     * This is overloaded method to log message with exception..
     *
     * @param message String message to log.
     */
    public static void debug(String message, Throwable e) {
        log.debug(message, e);
    }

}

