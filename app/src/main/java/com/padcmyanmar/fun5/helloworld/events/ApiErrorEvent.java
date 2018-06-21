package com.padcmyanmar.fun5.helloworld.events;

public class ApiErrorEvent {
    private String errorMessage;

    public ApiErrorEvent(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
