package bank.business;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

/**
 *Generates a text file with anything the LogHandler sends him.
 */
public class BankLogger {
    String filePatternMac = "%h";
    String filePatternWindows = "C:/Users/KSSA/Desktop/MyLogFile_";
    private Logger logger = Logger.getLogger(BankLogger.class.getName());
    private FileHandler fh = null;

    public BankLogger(String name) {
        SimpleDateFormat format = new SimpleDateFormat("M-d_HH-mm-ss");
        //TODO: Choose according filePattern before running (test purposes)
        try {
            fh = new FileHandler(filePatternMac + name + format.format(Calendar.getInstance().getTime()) + ".log");
        } catch (Exception e) {
            e.printStackTrace();
        }
        fh.setFormatter(new SimpleFormatter());
        logger.setLevel(java.util.logging.Level.ALL);
        //TODO: Uncomment next line to disable console logging; useful for Bank testing purposes
        //logger.setUseParentHandlers(false);
        logger.addHandler(fh);
    }


    public void doInfoLogging(String info) {
        logger.info(info);
    }

    public void doErrorLogging(String error) {
        logger.severe(error);
    }
}