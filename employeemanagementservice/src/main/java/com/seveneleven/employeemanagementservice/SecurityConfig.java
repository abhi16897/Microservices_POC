package com.seveneleven.employeemanagementservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.seveneleven.employeemanagementservice.security.JwtAuthorizationFilter;
import com.seveneleven.employeemanagementservice.security.MyUserDetailService;

import io.jsonwebtoken.Claims;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	@Autowired
	private MyUserDetailService myUserDetailService;
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception{
		auth.userDetailsService(myUserDetailService);
	}
	 @Bean
     public PasswordEncoder passwordEncoder() {
            return NoOpPasswordEncoder.getInstance();
     }
	 
	 @Override
	 public void configure(HttpSecurity http)throws Exception{
		 http.cors();
		 http.csrf().disable().httpBasic().and().authorizeRequests()
		 .antMatchers("/authenticate").permitAll();
		 http.csrf().disable().httpBasic().and().authorizeRequests()
		 .antMatchers("/allemployees").permitAll()
		 .antMatchers("/employeeadd").permitAll()
		 .antMatchers("/update").permitAll()
		 .antMatchers("/getbyemp").permitAll()
		 .antMatchers("/getByid").permitAll()
		 .antMatchers("/delete").permitAll()
		 .anyRequest().authenticated().and().addFilter(new JwtAuthorizationFilter(authenticationManager()));
	 }
}
