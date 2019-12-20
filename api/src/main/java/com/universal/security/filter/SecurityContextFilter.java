package com.universal.security.filter;

import com.universal.constants.CommonConstants;
import com.universal.entity.provider.RequestAttributeProvider;
import com.universal.entity.provider.SessionContextProvider;
import com.universal.entity.response.security.SessionInfo;
import com.universal.utils.Util;
import org.slf4j.MDC;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.IOException;

public class SecurityContextFilter extends GenericFilterBean {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {

        try {
            SessionContextProvider.reset();
            HttpServletRequestWrapper request = (HttpServletRequestWrapper) servletRequest;
            RequestAttributeProvider.set(request);
            /*String sessionId = (String) RequestAttributeProvider
                    .getValue(CommonConstants.SessionConstants.SESSION_REQUEST_HEADER);
            String visitId = (String) RequestAttributeProvider
                    .getValue(CommonConstants.SessionConstants.VISIT_REQUEST_HEADER);
            SessionInfo sessionInfo = null;*/
            /*if (!Util.isNullOrEmpty(sessionId)) {
                try {
                    MDC.put("loggingId", sessionId);
                    MDC.put("uri", ((HttpServletRequest) servletRequest).getRequestURI());

                    sessionInfo = sessionRepository.fetchSessionInfo(sessionId);
                    logger.debug("session info for session id" + sessionId + " is " + Util.convertObjectToString(sessionInfo));
                    SessionContextProvider.set(sessionInfo);
                    SessionContextProvider.setVisitId(visitId);
                } catch (Exception ex) {
                    logger.info("Error occured while fetching session", ex);
                }
            }*/
            filterChain.doFilter(servletRequest, servletResponse);
        } finally {
            SessionContextProvider.reset();
            RequestAttributeProvider.reset();
            MDC.remove("loggingId");
            MDC.remove("uri");
        }
    }
}
