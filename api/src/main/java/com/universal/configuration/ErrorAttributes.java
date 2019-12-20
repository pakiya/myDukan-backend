package com.universal.configuration;

import com.universal.exception.ServiceExceptionCodes;
import org.springframework.boot.autoconfigure.web.DefaultErrorAttributes;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.context.request.RequestAttributes;

import javax.servlet.ServletException;
import java.util.HashMap;
import java.util.Map;

public class ErrorAttributes extends DefaultErrorAttributes {

    private static final String ERORR_CODE = "erorrCode";
    private static final String ERROR_MESSAGE_KEY = "message";

    @Override
    public Map<String, Object> getErrorAttributes(RequestAttributes requestAttributes, boolean includeStackTrace) {
        /*Map<String, Object> errorAtrributes = new HashMap<>();
        addErrorDetails(errorAtrributes, requestAttributes);
        if (errorAtrributes.get(ERROR_MESSAGE_KEY) == null) {
            errorAtrributes.put(ERROR_MESSAGE_KEY, ServiceExceptionCodes.BAD_REQUEST.getErrorMessage());
        }
        errorAtrributes.put(ERORR_CODE, ServiceExceptionCodes.BAD_REQUEST.getErrorCode());
        String errorMessage = (String) errorAtrributes.get(ERROR_MESSAGE_KEY);
        if (org.apache.commons.lang.StringUtils.containsIgnoreCase(errorMessage,
                "Multipart Mime part image exceeds max filesize")) {
            errorAtrributes.put(ERROR_MESSAGE_KEY, ServiceExceptionCodes.FILE_SIZE_EXCEEDED.getErrorMessage());
            errorAtrributes.put(ERORR_CODE, ServiceExceptionCodes.FILE_SIZE_EXCEEDED.getErrorCode());
        }*/
//        return errorAtrributes;
        return null;
    }

    private void addErrorDetails(Map<String, Object> errorAttributes, RequestAttributes requestAttributes) {
        Throwable error = getError(requestAttributes);
        if (error != null) {
            while (error instanceof ServletException && error.getCause() != null) {
                error = error.getCause();
            }
            addErrorMessage(errorAttributes, error);
        }
        Object message = getAttribute(requestAttributes, "javax.servlet.error.message");
        if ((!StringUtils.isEmpty(message) || errorAttributes.get(ERROR_MESSAGE_KEY) == null)
                && !(error instanceof BindingResult)) {
            errorAttributes.put(ERROR_MESSAGE_KEY, message);
        }
    }

    private void addErrorMessage(Map<String, Object> errorAttributes, Throwable error) {
        if (!(error instanceof BindingResult)) {
            errorAttributes.put(ERROR_MESSAGE_KEY, error.getMessage());
            return;
        }
        BindingResult result = (BindingResult) error;
        if (result.getErrorCount() > 0) {
            errorAttributes.put("errors", result.getAllErrors());
            errorAttributes.put(ERROR_MESSAGE_KEY, "Validation failed for object='" + result.getObjectName()
                    + "'. Error count: " + result.getErrorCount());
        } else {
            errorAttributes.put(ERROR_MESSAGE_KEY, "No errors");
        }
    }

    @SuppressWarnings("unchecked")
    private <T> T getAttribute(RequestAttributes requestAttributes, String name) {
        return (T) requestAttributes.getAttribute(name, RequestAttributes.SCOPE_REQUEST);
    }
}
