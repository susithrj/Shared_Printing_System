package susith.cwk;
/**
 * *****************************************************************
 * File:      PrintingSystem.java
 * Author:    Susith Hemathilaka
 * Contents:  6SENG002W CWK
 * This defines the shared printer with students and technicians.
 * Date:      21/12/20
 * *****************************************************************
 */

import susith.cwk.util.UtilLogger;

public class LaserPrinter implements ServicePrinter {
    //private data members
    private String name;
    private int tonerLevel;
    private int papersAvailable;
    private int noofDocsprinted;
    private ThreadGroup userGroup;

    public LaserPrinter(String name, ThreadGroup userGroup) {
        this.name = name;
        this.tonerLevel = Full_Toner_Level;
        this.papersAvailable = Full_Paper_Tray;
        this.noofDocsprinted = 0;
        this.userGroup = userGroup;
    }


    @Override
    public synchronized void printDocument(Document document) {

        // wait until resources become sufficient
        while (papersAvailable < document.getNumberOfPages() || tonerLevel < document.getNumberOfPages()) {

            String message;
            //indicate the insufficient resources
            if (tonerLevel < document.getNumberOfPages()) {
                message = "Toner Level is Insufficient.Waiting until cartridge is replaced";
            } else if (papersAvailable < document.getNumberOfPages()) {
                message = "Insufficient Papers. Waiting until refilled";
            } else {
                message = "Insufficient Papers & Toner level. Waiting until they become available";
            }

            UtilLogger.log("LASERPRINTER", message);

            //wait
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        // Print document if resources are sufficient
        if (papersAvailable >= document.getNumberOfPages() && tonerLevel >= document.getNumberOfPages()) {
            papersAvailable -= document.getNumberOfPages();
            tonerLevel -= document.getNumberOfPages();
            noofDocsprinted++;

            UtilLogger.log("LASERPRINTER", "Printed document: " + document);
            UtilLogger.log("LASERPRINTER", toString());
        }
        notifyAll();
    }

    @Override
    public synchronized void replaceTonerCartridge() {

        UtilLogger.log("LASERPRINTER", "replace toner check");

        //  check in 5 seconds time intervals for the need to replace toner cartridge.
        while (tonerLevel > Minimum_Toner_Level) {
            // Check if printer has finished serving all the userGroup
            if (isPrinterFinished()) {
                UtilLogger.log("LASERPRINTER", "Usage of the printer is complete. No need to replace toner cartridge");
                break;
            } else {
                UtilLogger.log("LASERPRINTER", toString());
                UtilLogger.log("LASERPRINTER", "Toner has not reached the minimum level to be refilled. Waiting to check again");

                try {
                    wait(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        // Replace toner cartridge if it exceed minimum toner level
        if (tonerLevel < Minimum_Toner_Level) {
            tonerLevel = Full_Toner_Level;

            UtilLogger.log("LASERPRINTER", "Replaced toner cartridge");
            UtilLogger.log("LASERPRINTER", toString());
        }
        notifyAll();
    }

    @Override
    public synchronized void refillPaper() {

        UtilLogger.log("LASERPRINTER", "refill paper check");

        // Repeatedly check in 5 seconds time intervals for refilling paper.
        while (papersAvailable + SheetsPerPack > Full_Paper_Tray) {
            if (isPrinterFinished()) {
                UtilLogger.log("LASERPRINTER", "Usage of the printer is complete. No need to refill paper");
                break;
            } else {
                UtilLogger.log("LASERPRINTER", toString());
                UtilLogger.log("LASERPRINTER", "Refilling paper will exceed the capacity. Waiting to check again");

                try {
                    wait(5000); //5 seconds
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        // Refill paper when full paper tray + sheets per pack not exceeding maximum paper limit
        if (papersAvailable + SheetsPerPack <= Full_Paper_Tray) {
            UtilLogger.log("LASERPRINTER", toString());

            papersAvailable += SheetsPerPack;
            UtilLogger.log("LASERPRINTER", "Refilled Papers");
            UtilLogger.log("LASERPRINTER", toString());
        }
        notifyAll();
    }

    //return true is there no active threads
    private boolean isPrinterFinished() {
        return userGroup.activeCount() == 0;

    }

    @Override
    public String toString() {
        return " PRINTER :{" +
                "name='" + name + '\'' +
                ", tonerlevel=" + tonerLevel +
                ", papersavailable=" + papersAvailable +
                ", noofDocsprinted=" + noofDocsprinted +
                '}';
    }


}
