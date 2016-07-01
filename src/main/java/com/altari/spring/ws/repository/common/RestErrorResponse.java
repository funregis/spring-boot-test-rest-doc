package com.altari.spring.ws.repository.common;

public class RestErrorResponse {
    private String message;
    
    public RestErrorResponse(String aMessage) {
        super();
        message = aMessage;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String aMessage) {
        message = aMessage;
    }   
}
