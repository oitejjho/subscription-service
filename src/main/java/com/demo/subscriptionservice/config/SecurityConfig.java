package com.demo.subscriptionservice.config;

import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@EnableGlobalMethodSecurity(prePostEnabled = true)
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private SubscriptionServiceConfig subscriptionServiceConfig;

    private AuthenticationEntryPoint authenticationEntryPoint;

    private TokenFilter tokenFilter;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .antMatcher("/api/**")
                .authorizeRequests()
                .anyRequest().authenticated()
                .and()
                .httpBasic().disable()
                .csrf().disable()
                .anonymous().disable()
                .logout().disable()
                .rememberMe().disable()
                .formLogin().disable()
                .securityContext().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
//                .exceptionHandling().authenticationEntryPoint(authenticationEntryPoint)
//                .and()
                .headers().cacheControl().disable()
                .and()
                .addFilterAfter(tokenFilter, BasicAuthenticationFilter.class);
    }

}
