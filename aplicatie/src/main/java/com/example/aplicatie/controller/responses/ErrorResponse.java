package com.example.aplicatie.controller.responses;

public class ErrorResponse {
    String message;

    public ErrorResponse() {
        this.message = "";
    }

    public ErrorResponse(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
