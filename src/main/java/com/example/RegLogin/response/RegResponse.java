package com.example.RegLogin.response;

public class RegResponse {
    String message;
    Boolean error;

    public RegResponse(String message, Boolean error) {
        this.message = message;
        this.error = error;
    }

    public String getMessage() {
        return message;
    }

    public Boolean getError() {
        return error;
    }

}
