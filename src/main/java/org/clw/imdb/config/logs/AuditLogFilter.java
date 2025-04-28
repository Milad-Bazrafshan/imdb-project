package org.clw.imdb.config.logs;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;

import java.io.IOException;
import java.util.Date;
import java.util.logging.Logger;

/**
 * ************
 * AuditLogFilter
 * ************
 * <p>
 * This class serves as a filter for capturing
 * and logging audit information of incoming HTTP
 * requests. The captured details include user
 * identification, request URI, and HTTP method.
 * <p>
 * Developed by: Milad Bazrafshan
 * Date: 2025/01/10 - 01:35
 * <p>
 * Note: Ensure that this filter is registered in
 * the web.xml configuration file to be active.
 * <p>
 * Stay awesome, and happy coding!
 * <p>
 * ************
 */

public class AuditLogFilter implements Filter {
    private static final Logger logger = Logger.getLogger(AuditLogFilter.class.getName());

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    /**
     * ************
     * AuditLogFilter
     * ************
     * <p>
     * This Method is get http request data and print log
     * <p>
     * Stay awesome, and happy coding!
     * <p>
     * ************
     */

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        String user = httpServletRequest.getRemoteUser();
        String uri = httpServletRequest.getRequestURI();
        String method = httpServletRequest.getMethod();
        String ip = httpServletRequest.getRemoteAddr();
        // Log audit information
        logger.info("SecurityUser: " + user + " Uri: " + uri + " Method: " + method + " Ip: " + ip + " inDate: " + new Date());
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {
    }
}
