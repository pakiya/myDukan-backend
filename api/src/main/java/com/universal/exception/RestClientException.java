package com.universal.exception;

import java.util.Map;

public class RestClientException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    private String message;
    private String responseBody;
    private int responseCode;
    private Map<String, String> responseHeaders;


    public RestClientException(String message, String responseBody, int responseCode,
                               Map<String, String> responseHeaders) {
        super(message);
        this.responseBody = responseBody;
        this.responseCode = responseCode;
        this.responseHeaders = responseHeaders;
    }

    public RestClientException(String message, Throwable cause) {
        super(message);
    }

    public String getMessage() {
        return message;
    }

    public String getResponseBody() {
        return responseBody;
    }

    public int getResponseCode() {
        return responseCode;
    }

    public Map<String, String> getResponseHeaders() {
        return responseHeaders;
    }

    @Override
    public String toString() {
        return "RestClientException [message=" + message + ", responseBody=" + responseBody + ", responseCode="
                + responseCode + ", responseHeaders=" + responseHeaders + "]";
    }
}
