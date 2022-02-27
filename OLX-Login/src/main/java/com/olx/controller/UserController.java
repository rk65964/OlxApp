package com.olx.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.olx.dto.AuthenticationRequest;
import com.olx.sevice.UserService;
import com.zensar.security.JwtUtil;

@RestController
@RequestMapping("/olx")
@CrossOrigin(origins="*")
public class UserController {
	
	@Autowired
	UserService userService;
	@Autowired
	AuthenticationManager authenticationManager;
	
	@Autowired
	UserDetailsService userDetailsService;
	
	@Autowired
	JwtUtil jwtUtil;
	// 1
	@PostMapping(value = "/user/authenticate", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> Authenticate(@RequestBody AuthenticationRequest authenticationRequest)
			throws Exception {

		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
					authenticationRequest.getUserName(), authenticationRequest.getPassword()));
		} catch (AuthenticationException e) {
			throw new Exception(); // InvalidUserCredential
		}
		String authToken = jwtUtil.generateToken(authenticationRequest.getFirstName());
		return new ResponseEntity<String>(authToken, HttpStatus.OK);
	}
	//OLX-Login validateToken endpoint:
	@GetMapping(value= "/token/validate")
	public ResponseEntity<Boolean> validateToken(@RequestHeader("Autherization") String autToken) {
		// authToken ="Bearer xxxxx"
		String username = jwtUtil.extractUsername(autToken);
		UserDetails userDetails = userDetailsService.loadUserByUsername(username);
		boolean isValid = jwtUtil.validateToken(autToken,userDetails);
		return new ResponseEntity<Boolean>(isValid, HttpStatus.OK) ;
	}
	// 2
	@DeleteMapping(value= "/user/logout")
	public boolean userLogout(@RequestHeader("auth-token")String authToken) {
		return userService.userLogout(authToken);
	}
	
	// done 3
	@PostMapping(value= "/user/register", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<AuthenticationRequest> register(@RequestBody AuthenticationRequest authenticationRequest) {
		return new ResponseEntity<AuthenticationRequest>(userService.createUser(authenticationRequest), HttpStatus.OK);
	}
	
	//inprogress 4
	@GetMapping(value = "/user/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<AuthenticationRequest> getUserInformation(@PathVariable("id") int userId) {
		return new ResponseEntity<AuthenticationRequest>(userService.getUserkById(userId), HttpStatus.OK);

	}


}
