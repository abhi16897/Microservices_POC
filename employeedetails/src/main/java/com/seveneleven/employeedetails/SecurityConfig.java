package com.seveneleven.employeedetails;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import com.seveneleven.employeedetails.security.JwtAuthorizationFilter;

import io.jsonwebtoken.Claims;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{

	 
	 @Override
	 public void configure(HttpSecurity http)throws Exception{
		 http.cors();
		 http.csrf().disable().httpBasic().and().authorizeRequests()
		 .antMatchers("/addemployees").permitAll()
		 .antMatchers("/displayemployees").permitAll()
		 .antMatchers("/updateemployee").permitAll()
		 .antMatchers("/deleteemployee").permitAll()
		 .antMatchers("/getByid").permitAll()
		 .and().addFilter(new JwtAuthorizationFilter(authenticationManager()));
	 }
}
