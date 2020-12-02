package com.weather.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.weather.configuration.jwtSecurity.CredentialService;
import com.weather.configuration.jwtSecurity.JwtAuthEntryPoint;
import com.weather.configuration.jwtSecurity.JwtAuthTokenFilter;

@Configuration
@EnableWebSecurity
public class common_security_config extends WebSecurityConfigurerAdapter{

		
				
	@Autowired
	private CredentialService CredentialService;	


		@Autowired
		  public void configAuthentication(AuthenticationManagerBuilder auth) throws Exception {
			     
			       auth.userDetailsService(CredentialService).passwordEncoder( new BCryptPasswordEncoder());
		  }

	    @Autowired
		    private JwtAuthEntryPoint unauthorizedHandler;

		    @Bean
		    public JwtAuthTokenFilter authenticationJwtTokenFilter() {
		        return new JwtAuthTokenFilter();
		    }

		  
		    @Bean
		    @Override
		    public AuthenticationManager authenticationManagerBean() throws Exception {
		        return super.authenticationManagerBean();
		    }

		  
		    
		    @Override
		    protected void configure(HttpSecurity http) throws Exception {
		        http.cors().and().csrf().disable().authorizeRequests()
		                .antMatchers("/unauth/**").permitAll()
		                .anyRequest().authenticated()
		                .and()
		                //.exceptionHandling().authenticationEntryPoint(unauthorizedHandler).and()
		                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		        http.addFilterBefore(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);
		        System.out.println("Security API Configuration Done...");
		    }
			

	
}


