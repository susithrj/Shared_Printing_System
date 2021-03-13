package susith.cwk.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;


public class UtilLogger {

    public static synchronized void log(String workerID, String message) {

        Date date = new Date(System.currentTimeMillis());
        DateFormat formatter = new SimpleDateFormat("HH:mm:ss.SSS");
        String logTime = formatter.format(date);

        System.out.println("[" + logTime + "]  " + "[" + workerID + "] " + (message));
    }
}
