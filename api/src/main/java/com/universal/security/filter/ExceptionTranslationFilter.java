package com.universal.security.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.universal.configuration.MetricsConfiguration;
import com.universal.entity.response.ErrorResponse;
import com.universal.exception.ApiServiceException;
import com.universal.exception.ServiceExceptionCodes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
import java.io.IOException;

public class ExceptionTranslationFilter extends GenericFilterBean {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MetricsConfiguration metricsConfiguration;

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        HttpServletResponseWrapper response = (HttpServletResponseWrapper) servletResponse;
        try {
            filterChain.doFilter(servletRequest, servletResponse);
        } catch (ApiServiceException ex) {
            errorResponse(response, ex);
        } catch (Exception ex) {
            logger.error("Exception occured ", ex);
            errorResponse(response, new ApiServiceException(ServiceExceptionCodes.ERROR_SYSTEM_ERROR));
        }
    }

    private void errorResponse(HttpServletResponseWrapper response, ApiServiceException ex) throws IOException {
        ErrorResponse errorResponse = new ErrorResponse(ex.getErrorCode().getErrorCode(), ex.getErrorMessage());

        String responseString = objectMapper.writeValueAsString(errorResponse);
        response.getWriter().write(responseString);
        response.setStatus(ex.getErrorCode().getHttpStatus().value());
        response.setContentType(String.valueOf(MediaType.APPLICATION_JSON));
        response.getWriter().flush();

        metricsConfiguration.getMetricRegistry().meter(ex.getErrorCode().name()).mark();
        metricsConfiguration.getMetricRegistry().meter("ALL_EXCEPTION").mark();
    }

}
