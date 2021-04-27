package com.info.test.springboot.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.info.test.springboot.model.UserTbl;
import com.info.test.springboot.repository.UserRepository;
import com.info.test.springboot.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserRepository userRepository;

	@Override
	public UserTbl users(UserTbl userTbl) {
		return userRepository.save(userTbl);
	}

}
