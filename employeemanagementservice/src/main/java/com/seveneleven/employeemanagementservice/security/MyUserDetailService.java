package com.seveneleven.employeemanagementservice.security;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.seveneleven.employeemanagementservice.EmployeemanagementserviceApplication;
import com.seveneleven.employeemanagementservice.model.Users;
import com.seveneleven.employeemanagementservice.repository.UserRepository;



@Service
public class MyUserDetailService implements UserDetailsService{
	@Autowired
	UserRepository userRepository;
	private static final Logger LOGGER = LoggerFactory.getLogger(EmployeemanagementserviceApplication.class);
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		Users user=userRepository.findByUsername(username);
		LOGGER.debug("User:{}", user);
		LOGGER.info("Hello");
		if(user!=null) {
			return new MyUserDetails(user);
		}else {
			throw new UsernameNotFoundException(username);
		}
	}
	
}
