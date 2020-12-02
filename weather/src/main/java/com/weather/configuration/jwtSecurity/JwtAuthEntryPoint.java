package com.weather.configuration.jwtSecurity;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import com.weather.configuration.ExceptionHandler.ApiException;

@Component
public class JwtAuthEntryPoint implements AuthenticationEntryPoint {

    private static final Logger logger = LoggerFactory.getLogger(JwtAuthEntryPoint.class);
    
    @Override
    public void commence(HttpServletRequest request,
                         HttpServletResponse response,
                         AuthenticationException e) 
                        		 throws IOException, ServletException {
    	System.out.println("In Auth Entry Point with error   "+e.getMessage());
        logger.error("Unauthorized error. Message - {}", e.getMessage());
        //response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Error -> "+e.getMessage());
        //e.printStackTrace();
        throw new ApiException("Error -> "+e.getMessage(),HttpServletResponse.SC_UNAUTHORIZED);
    }
}