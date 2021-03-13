package susith.cwk;

/**
 * *****************************************************************
 * File:      PrintingSystem.java
 * Author:    Susith Hemathilaka
 * Contents:  6SENG002W CWK
 * This defines the paper technician who refill papers of printer.
 * Date:      21/12/20
 * *****************************************************************
 */

import susith.cwk.util.UtilLogger;

public class PaperTechnician extends Thread {
    //private data members
    private Printer printer;

    public PaperTechnician(String name, Printer printer, ThreadGroup threadGroup) {
        super(threadGroup, name);
        this.printer = printer;
    }

    @Override
    public void run() {
        //paper technician try three times to refill papers
        for (int i = 0; i < 3; i++) {
            UtilLogger.log("PAPERTECHNICIAN", "Paper technician started :" + this.getName());
            ((LaserPrinter) printer).refillPaper();
        }
    }


}
