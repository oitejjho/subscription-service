package com.demo.subscriptionservice.filter;

import com.demo.subscriptionservice.config.SubscriptionServiceConfig;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.ThreadContext;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class CorrelationIdFilter extends OncePerRequestFilter {

    private final SubscriptionServiceConfig subscriptionServiceConfig;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws IOException, ServletException {

        String correlationId = request.getHeader(subscriptionServiceConfig.getCorrelationIdKey());

        if (correlationId == null || correlationId.trim().isEmpty()) {
            correlationId = String.format("SS-%s", UUID.randomUUID().toString().replace("-", "").toLowerCase());
        }

        ThreadContext.put(subscriptionServiceConfig.getCorrelationIdKey(), correlationId.trim());

        filterChain.doFilter(request, response);

    }

}
