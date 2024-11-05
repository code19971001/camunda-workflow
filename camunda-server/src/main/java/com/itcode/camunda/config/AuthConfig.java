package com.itcode.camunda.config;

import jakarta.servlet.FilterRegistration;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import org.camunda.bpm.engine.rest.security.auth.ProcessEngineAuthenticationFilter;
import org.springframework.boot.web.servlet.ServletContextInitializer;
import org.springframework.context.annotation.Configuration;

import static org.camunda.bpm.engine.rest.security.auth.ProcessEngineAuthenticationFilter.AUTHENTICATION_PROVIDER_PARAM;

@Configuration
public class AuthConfig implements ServletContextInitializer {
    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        FilterRegistration.Dynamic authFilter = servletContext.addFilter("camunda-auth", ProcessEngineAuthenticationFilter.class);
        authFilter.setAsyncSupported(true);
        authFilter.setInitParameter(AUTHENTICATION_PROVIDER_PARAM, "org.camunda.bpm.engine.rest.security.auth.impl.HttpBasicAuthenticationProvider");
        authFilter.addMappingForUrlPatterns(null, true, "/engine-rest/*");
    }
}
