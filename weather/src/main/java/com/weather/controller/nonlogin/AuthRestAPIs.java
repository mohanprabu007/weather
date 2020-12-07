package com.weather.controller.nonlogin;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.weather.configuration.jwtSecurity.JwtProvider;
import com.weather.configuration.jwtSecurity.JwtResponse;
import com.weather.model.LoginForm;

//@CrossOrigin(origins = "*", maxAge = 36000)
@RestController
@RequestMapping("/unauth")
public class AuthRestAPIs {

    @Autowired
    AuthenticationManager authenticationManager;
   @Autowired
   JwtProvider jwtProvider;

    @PostMapping("/getToken")
    public ResponseEntity<?> authenticateUser(@RequestBody LoginForm login,HttpServletRequest request ) {


        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                		login.getUserName(),
                		login.getPassword()
                )
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String name = authentication.getName(); //get logged in username
	  
	      String jwt = jwtProvider.generateJwtToken(authentication);
	   	  return ResponseEntity.ok(new JwtResponse(jwt));
    }


}