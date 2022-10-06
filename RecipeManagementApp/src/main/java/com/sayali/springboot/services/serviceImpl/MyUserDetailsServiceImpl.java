package com.sayali.springboot.services.serviceImpl;

import java.util.ArrayList;

//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsServiceImpl implements UserDetailsService {

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		
		UserDetails myUser = User.withUsername("myUser").password("mypass").disabled(false).authorities(new ArrayList<>()).build();

		if (!username.isEmpty()) {
		myUser = User.withUsername(username).password("mypass").disabled(false)
				.authorities(new ArrayList<>()).build();
		} else {
			
		}

		return myUser;
	}

}
