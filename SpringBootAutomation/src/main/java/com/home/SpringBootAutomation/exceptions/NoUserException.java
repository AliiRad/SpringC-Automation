package com.home.SpringBootAutomation.exceptions;

public class NoUserException  extends Exception{

    private String message;

    public NoUserException(){
        message = "No User Found !";
    }

    public NoUserException(String message){
        super(message);
        this.message = message;
    }
    public int responseStatus(){return 204;}
}
