package com.home.SpringBootAutomation.exceptions;

public class DuplicateDataException extends Exception{

    /*409 is the correct status code for duplicate resource or resource already exists.*/
    private String message;

    public DuplicateDataException() {
        message = "Duplicate Data";
    }

    public DuplicateDataException(String message) {
        super(message);
        this.message = message;
    }
    public int responseStatus(){return 409;}

}
