package org.clw.imdb.config.filter;

import org.clw.imdb.config.logs.AuditLogFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * ************
 * FilterConfig
 * ************
 * <p>
 * This configuration class is responsible for
 * registering and configuring the AuditLogFilter
 * across all modules of the project. The filter
 * captures and logs essential audit information
 * such as user identification, request URI,
 * and HTTP method for incoming HTTP requests.
 * <p>
 * Developed by: Milad Bazrafshan
 * Date: 2025/01/10 - 01:38
 * <p>
 * Note: Ensure that the common-module dependency is
 * correctly added to all relevant modules to activate
 * the audit logging functionality.
 * <p>
 * Stay sharp, code smart, and keep logging!
 * <p>
 * ************
 */

@Configuration
public class FilterConfig {
    @Bean
    public FilterRegistrationBean<AuditLogFilter> auditLogFilter() {
        FilterRegistrationBean<AuditLogFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new AuditLogFilter());
        registrationBean.addUrlPatterns("/*");
        return registrationBean;
    }
}
