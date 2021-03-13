package susith.cwk;

/**
 * *****************************************************************
 * File:      PrintingSystem.java
 * Author:    Susith Hemathilaka
 * Contents:  6SENG002W CWK
 * This defines the toner technician who replace toners of the printer.
 * Date:      21/12/20
 * *****************************************************************
 */

import susith.cwk.util.UtilLogger;

public class TonerTecnician extends Thread {
    //private data members
    private Printer printer;

    public TonerTecnician(String name, Printer printer, ThreadGroup threadGroup) {
        super(threadGroup, name);
        this.printer = printer;
    }

    @Override
    public void run() {
        //toner technician tries 3 time to replace toner cartridge
        for (int i = 0; i < 3; i++) {
            UtilLogger.log("TONERTECHNICIAN", "Toner technician started :" + this.getName());
            ((LaserPrinter) printer).replaceTonerCartridge();
        }
    }
}
