package com.weather.configuration.jwtSecurity;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import com.weather.configuration.exceptionHandler.ApiException;
import com.weather.configuration.exceptionHandler.ApiGlobalException;

@Component
public class JwtAuthEntryPoint implements AuthenticationEntryPoint {

	 private static final Logger LOGGER = LogManager.getLogger(JwtAuthEntryPoint.class);
    
    @Override
    public void commence(HttpServletRequest request,
                         HttpServletResponse response,
                         AuthenticationException e) 
                        		 throws IOException, ServletException {
    	
    	LOGGER.error("Unauthorized error. Message - {}", e.getMessage());
        throw new ApiException("Error -> "+e.getMessage(),HttpServletResponse.SC_UNAUTHORIZED);
    }
}