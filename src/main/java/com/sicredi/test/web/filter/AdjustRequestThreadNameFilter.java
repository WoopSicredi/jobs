package com.sicredi.test.web.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * Componente responsável por setar nome da thread corrente com a URL do request HTTP.
 * @author dalpiaz
 */
@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
class AdjustRequestThreadNameFilter implements Filter {

    private static final String FULL_URL_PATTERN = "%s?%s"; //$NON-NLS-1$
    private static final String THREAD_NAME_PATTERN = "%s %s %s"; //$NON-NLS-1$

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {}

    @Override
    public void destroy() {}

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        Thread requestThread = Thread.currentThread();
        String previousThreadName = requestThread.getName();

        try {
            requestThread.setName(formatRequestThreadName(previousThreadName, httpRequest));
            chain.doFilter(request, response);
        } finally {
            requestThread.setName(previousThreadName);
        }
    }

    private static String formatRequestThreadName(String previousThreadName, HttpServletRequest httpRequest) {
        return String.format(THREAD_NAME_PATTERN, previousThreadName,
                httpRequest.getMethod(), extractFullUrlFrom(httpRequest));
    }

    private static String extractFullUrlFrom(HttpServletRequest request) {
        if (request.getQueryString() == null) {
            return request.getRequestURL().toString();
        }
        return String.format(FULL_URL_PATTERN, request.getRequestURL().toString(), request.getQueryString());
    }
}
