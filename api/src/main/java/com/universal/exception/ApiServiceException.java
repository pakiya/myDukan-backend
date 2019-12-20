package com.universal.exception;

import com.universal.utils.Util;

public class ApiServiceException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    private ServiceExceptionCodes errorCode;
    private String errorMessage;

    public ApiServiceException(ServiceExceptionCodes errorCode) {
        super(errorCode.toString());
        this.errorCode = errorCode;
        this.errorMessage = errorCode.getErrorMessage();
    }

    public ServiceExceptionCodes getErrorCode() {
        return errorCode;
    }

    public String getErrorMessage() {
        if (Util.isNullOrEmpty(errorMessage))
            return errorCode.getErrorMessage();
        return errorMessage;
    }

    public ApiServiceException(Throwable cause, ServiceExceptionCodes errorCode) {
        super(cause);
        this.errorCode = errorCode;
        this.errorMessage = cause.getMessage();
    }
}
