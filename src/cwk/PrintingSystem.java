package susith.cwk;

/**
 * *****************************************************************
 * File:      PrintingSystem.java
 * Author:    Susith Hemathilaka
 * Contents:  6SENG002W CWK
 * This defines the printing system for students, technicians and shared printer.
 * Date:      21/12/20
 * *****************************************************************
 */

import susith.cwk.util.UtilLogger;


public class PrintingSystem {

    public static void main(String[] args) {

        UtilLogger.log("PRINTINGSYSTEM", "Printing System Started.");

        //create thread groups
        ThreadGroup groupStudents = new ThreadGroup("students");
        ThreadGroup groupTecnicians = new ThreadGroup("tecnicians");

        //create Laser printer instance
        LaserPrinter printer = new LaserPrinter("printer_A1", groupStudents);

        // create student threads
        Student student1 = new Student("Susith", printer, groupStudents);
        Student student2 = new Student("Saneth", printer, groupStudents);
        Student student3 = new Student("Milinda", printer, groupStudents);
        Student student4 = new Student("Rajitha", printer, groupStudents);

        // create instance of tonerTecnician
        TonerTecnician tonerTecnician = new TonerTecnician("Toner Technician", printer, groupTecnicians);
        // create instance of paperTecnician
        PaperTechnician paperTechnician = new PaperTechnician("Paper Technician", printer, groupTecnicians);

        //start student threads
        student1.start();
        UtilLogger.log("STUDENT", "started student : " + student1.getName());
        student2.start();
        UtilLogger.log("STUDENT", "started student : " + student2.getName());
        student3.start();
        UtilLogger.log("STUDENT", "started student : " + student3.getName());
        student4.start();
        UtilLogger.log("STUDENT", "started student : " + student4.getName());

        //start paper technician
        paperTechnician.start();
        //start toner technician
        tonerTecnician.start();

        //join all the students and technicians after execution
        try {
            student1.join();
            UtilLogger.log("PRINTINGSYSTEM", student1.getName() + ": completed printing.");
            student2.join();
            UtilLogger.log("PRINTINGSYSTEM", student2.getName() + ": completed printing.");
            student3.join();
            UtilLogger.log("PRINTINGSYSTEM", student3.getName() + ": completed printing.");
            student4.join();
            UtilLogger.log("PRINTINGSYSTEM", student4.getName() + ": completed printing.");
            paperTechnician.join();
            UtilLogger.log("PRINTINGSYSTEM", paperTechnician.getName() + " : completed refilling papers.");
            tonerTecnician.join();
            UtilLogger.log("PRINTINGSYSTEM", tonerTecnician.getName() + " : completed refilling toners.");

            // when all threads finished
            UtilLogger.log("PRINTINGSYSTEM", " All Threads Finished Working");
            UtilLogger.log("PRINTINGSYSTEM", " " + printer.toString());

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
