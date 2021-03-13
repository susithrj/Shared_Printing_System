package susith.cwk;

/**
 * *****************************************************************
 * File:      PrintingSystem.java
 * Author:    Susith Hemathilaka
 * Contents:  6SENG002W CWK
 * This defines the Document.
 * Date:      21/12/20
 * *****************************************************************
 */

class Document {
    private final String userID;
    private final String documentName;
    private final int numberOfPages;

    public Document(String UID, String name, int length) {
        this.userID = UID;
        this.documentName = name;
        this.numberOfPages = length;
    }

    public String getUserID() {
        return userID;
    }

    public String getDocumentName() {
        return documentName;
    }

    public int getNumberOfPages() {
        return numberOfPages;
    }


    public String toString() {
        return new String("Document[ " +
                "UserID: " + userID + ", " +
                "Name: " + documentName + ", " +
                "Pages: " + numberOfPages +
                "]");
    }

}


