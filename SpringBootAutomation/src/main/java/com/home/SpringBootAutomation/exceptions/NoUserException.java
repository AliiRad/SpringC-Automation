package com.home.SpringBootAutomation.exceptions;

public class NoUserException  extends TemplateException{

public NoUserException(String message){
    super(message);
    setStatusCode(404);
}
}
