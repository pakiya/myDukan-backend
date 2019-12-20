package com.universal.exception;


import com.universal.constants.CommonConstants;
import org.springframework.http.HttpStatus;

public enum ServiceExceptionCodes {

    ERROR_SYSTEM_ERROR(1001, CommonConstants.ErrorMessages.INTERNAL_SYSTEM_ERROR, HttpStatus.INTERNAL_SERVER_ERROR),

    BAD_REQUEST(1025, CommonConstants.ErrorMessages.BAD_REQUEST),
    TITLE_NULL_REQUEST(1026, CommonConstants.ErrorMessages.TITLE_NULL_REQUEST),
    INVALID_REQUEST(1029, CommonConstants.ErrorMessages.INVALID_REQUEST),
    SEARCH_INTERNAL_ERROR(1022, HttpStatus.INTERNAL_SERVER_ERROR),
    INVALID_SHEET_PAGINATION(1020, CommonConstants.ErrorMessages.INVALID_SHEET_PAGINATION),
    ;

    private HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
    private int errorCode;
    private String errorMessage = CommonConstants.ErrorMessages.INTERNAL_SYSTEM_ERROR;

    ServiceExceptionCodes(int errorCode, String errorMessage) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    ServiceExceptionCodes(int errorCode) {
        this.errorCode = errorCode;
    }

    ServiceExceptionCodes(int errorCode, String errorMessage, HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    ServiceExceptionCodes(int errorCode, HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
        this.errorCode = errorCode;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
