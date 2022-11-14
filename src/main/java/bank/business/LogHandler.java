package bank.business;

import bank.business.BankLogger;

public class LogHandler {
    private static BankLogger logger = new BankLogger("IOHandler");

    public static void doInputLogging (String input) {
        logger.doInfoLogging("User input: " + input);
    }

    public static void doOutputLogging (String output) {
        logger.doInfoLogging("User output: " + output);
    }

    public static void doErrorLogging (String error) {
        logger.doErrorLogging("User error: " + error);
    }

    public static void doEventLogging (String event) {
        logger.doInfoLogging("Event: " + event);
    }

    public static void doTransactionLogging (String transaction) {
        logger.doInfoLogging("Transaction: " + transaction);
    }

}