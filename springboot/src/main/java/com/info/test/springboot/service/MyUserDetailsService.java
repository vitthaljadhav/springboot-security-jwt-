package com.info.test.springboot.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.info.test.springboot.model.UserTbl;
import com.info.test.springboot.repository.UserRepository;

import com.info.test.springboot.repository.UserRepository;

@Service
public class MyUserDetailsService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		UserTbl users = userRepository.findByGmail(username);
		if(users==null) {
			throw new UsernameNotFoundException("User not found with username: " + username);
		}
		return new User(users.getGmail(), users.getPwd(), new ArrayList<>());
	}

}
