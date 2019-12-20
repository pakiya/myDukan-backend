package com.universal.entity.response;

public class ErrorResponse {

    private int errorCode;
    private String errorMessage;

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public ErrorResponse() {
        super();
    }

    public ErrorResponse(int i, String errorMessage) {
        super();
        this.errorCode = i;
        this.errorMessage = errorMessage;
    }
}
