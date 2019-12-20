package com.universal.security.filter;

import com.universal.config.ConfigManager;
import com.universal.entity.provider.RequestAttributeProvider;
import com.universal.exception.ApiServiceException;
import com.universal.exception.ServiceExceptionCodes;
import com.universal.utils.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.IOException;

public class AdminRequestValidateFilter extends GenericFilterBean {

    private static final String ADMIN_ACCESS_KEY_HEADER = "X-ADMIN-SECRET";

    private static final String ADMIN_SECRECT_KEY = "admin.secret.key";

    private static final String adminSecret = "adminSecret";

    @Autowired
    private ConfigManager configManager;

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        HttpServletRequestWrapper request = (HttpServletRequestWrapper) servletRequest;
        if ("OPTIONS".equalsIgnoreCase(request.getMethod())) {
            filterChain.doFilter(request, servletResponse);
        } else {
            String adminKey = request.getParameter(adminSecret);
            String adminHeaderKey = (String) RequestAttributeProvider.getValue(ADMIN_ACCESS_KEY_HEADER);
            if (!Util.isNullOrEmpty(adminKey) && adminKey.equals(configManager.getConfig(ADMIN_SECRECT_KEY))) {
                filterChain.doFilter(servletRequest, servletResponse);
            } else if (!Util.isNullOrEmpty(adminHeaderKey) && adminHeaderKey.equals(configManager.getConfig(ADMIN_SECRECT_KEY))) {
                filterChain.doFilter(servletRequest, servletResponse);
            } else {
                throw new ApiServiceException(ServiceExceptionCodes.INVALID_REQUEST);
            }
        }
    }
}
