package com.demo.subscriptionservice.config;

import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.demo.subscriptionservice.constants.StatusConstants.HttpConstants.BAD_CREDENTIALS;

@Service
@RequiredArgsConstructor
public class TokenFilter extends GenericFilterBean {

    public static final String AUTHORIZATION_HEADER_NAME = "X-API-KEY";

    private SubscriptionServiceConfig subscriptionServiceConfig;

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        authenticate(servletRequest);
        filterChain.doFilter(servletRequest, servletResponse);
    }

    private void authenticate(ServletRequest servletRequest) {

        HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
        String key = httpRequest.getHeader(AUTHORIZATION_HEADER_NAME);
        if (StringUtils.isNotBlank(key)) {
            if (key.equals(subscriptionServiceConfig.getApiKey())) {
                List<GrantedAuthority> grantedAuthorityList = new ArrayList<>();
                grantedAuthorityList.add(new SimpleGrantedAuthority(subscriptionServiceConfig.getServiceRole()));
                SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken("", null, grantedAuthorityList));
            } else {
                throw new BadCredentialsException(BAD_CREDENTIALS.getDesc());
            }
        }
    }
}
