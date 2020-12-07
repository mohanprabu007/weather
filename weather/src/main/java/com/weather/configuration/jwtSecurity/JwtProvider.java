package com.weather.configuration.jwtSecurity;

import java.util.Date;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.UnsupportedJwtException;

@Component
public class JwtProvider {

	private static final Logger LOGGER = LogManager.getLogger(JwtProvider.class);

    @Value("${weather.app.jwtSecret}")
    private String jwtSecret;

    @Value("${weather.app.jwtExpiration}")
    private int jwtExpiration;

    public String generateJwtToken(Authentication authentication) {

      

        try {
        	
			return Jwts.builder()
			                .setSubject((authentication.getName()))
			                .setIssuedAt(new Date())
			                .setExpiration(new Date((new Date()).getTime() + jwtExpiration*1000))
			                .signWith(SignatureAlgorithm.HS512, jwtSecret)
			                .setIssuer("weather")
			                .compact();
		} catch (Exception e) {e.printStackTrace();}
        return "";
    }
    
    public boolean validateJwtToken(String authToken) {
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authToken);
           // System.out.println("..token validate..."+authToken);
            return true;
        }catch (io.jsonwebtoken.SignatureException e) {
        	LOGGER.error("Invalid JWT signature -> Message: {} ", e);
        }  
        catch (MalformedJwtException e) {
        	LOGGER.error("Invalid JWT token -> Message: {}", e);
        } catch (ExpiredJwtException e) {
        	LOGGER.error("Expired JWT token -> Message: {}", e);
        } catch (UnsupportedJwtException e) {
        	LOGGER.error("Unsupported JWT token -> Message: {}", e);
        } catch (IllegalArgumentException e) {
        	LOGGER.error("JWT claims string is empty -> Message: {}", e);
        }
        catch (Exception e) {
        	LOGGER.error("Invalid JWT signature -> Message: {} ", e);
        } 
        return false;
    }
    
    public String getUserNameFromJwtToken(String token) {
        return Jwts.parser()
			                .setSigningKey(jwtSecret)
			                .parseClaimsJws(token)
			                .getBody().getSubject();
    }
}