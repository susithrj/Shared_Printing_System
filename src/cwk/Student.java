package susith.cwk;

/**
 * *****************************************************************
 * File:      PrintingSystem.java
 * Author:    Susith Hemathilaka
 * Contents:  6SENG002W CWK
 * This defines the student who use printer to print documents.
 * Date:      21/12/20
 * *****************************************************************
 */

import susith.cwk.util.UtilLogger;

import java.util.Random;

public class Student extends Thread {
    //private data members
    private Printer printer;

    public Student(String name, Printer printer, ThreadGroup threadGroup) {
        super(threadGroup, name);
        this.printer = printer;
    }

    @Override
    public void run() {
        //no of documents want to be printed for a student
        int noofdocuments = 5;

        for (int i = 0; i < noofdocuments; i++) {
            //randomize page count for each document
            Random random = new Random();
            int documentLength = 20;
            //give specific document name
            String documentName = "Cwkdoc_" + (i + 1);
            //document initialization
            Document documentCW = new Document
                    (this.getName() + "_" + i, this.getName() + "_" + documentName, documentLength);

            UtilLogger.log("STUDENT", this.getName() + " initiated new print operation");
            printer.printDocument(documentCW);

            //randomize sleep duration
            int sleepTime = random.nextInt(2000 - 1500) + 1500;
            try {
                sleep(sleepTime);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        UtilLogger.log("STUDENT",
                " current document printing finished for the student : " + this.getName());
    }
}
