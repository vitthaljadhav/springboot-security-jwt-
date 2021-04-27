package com.info.test.springboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.info.test.springboot.model.AuthenticationRequest;
import com.info.test.springboot.model.AuthenticationResponse;
import com.info.test.springboot.model.UserTbl;
import com.info.test.springboot.service.MyUserDetailsService;
import com.info.test.springboot.service.UserService;
import com.info.test.springboot.util.JwtUtil;

@RestController
public class HomeController {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private MyUserDetailsService userDetailsService;

	@Autowired
	private JwtUtil jwtUtil;
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(value = "/hello")
	public String show() {
		return "This is HomeController ";
	}

	//LOGIN
	@RequestMapping(value = "/authenticate", method = RequestMethod.POST)
	public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest request) throws Exception {
		try {
			authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
		} catch (BadCredentialsException e) {
			throw new Exception("Incorrect username or password", e);
		}

		final UserDetails usersDetails = userDetailsService.loadUserByUsername(request.getUsername());
	
		final String token = jwtUtil.generateToken(usersDetails);
		
		return ResponseEntity.ok(new AuthenticationResponse(token));
		
	}
	
	//Registration
	@RequestMapping(value = "/register",method = RequestMethod.POST)
	public ResponseEntity<?> welcomePage(@RequestBody UserTbl userTbl) {
		UserTbl users = userService.users(userTbl);
		return ResponseEntity.ok(users);
	}
}
