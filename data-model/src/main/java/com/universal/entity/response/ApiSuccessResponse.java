package com.universal.entity.response;

public class ApiSuccessResponse implements ApiResponse {
    private String status;

    public ApiSuccessResponse(String status) {
        super();
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}
