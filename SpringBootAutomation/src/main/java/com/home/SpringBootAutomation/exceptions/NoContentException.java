package com.home.SpringBootAutomation.exceptions;

public class NoContentException extends TemplateException{

    public NoContentException(String message) {
        super(message);
        setStatusCode(404);
    }

}
