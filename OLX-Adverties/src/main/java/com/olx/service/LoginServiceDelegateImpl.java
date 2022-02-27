package com.olx.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class LoginServiceDelegateImpl implements LoginServiceDelegate {

	@Autowired
	RestTemplate restTemplate;

	@Override
	public boolean isValidUser(String authToke) { // localhost:8080 =AUTH-SERVICE through eureka sewrver
		// using rest template call /Token/validate on OLX-Login
		HttpHeaders headers = new HttpHeaders();
		headers.set("Authorization", authToke);
		HttpEntity entity = new HttpEntity(headers);
		ResponseEntity<Boolean> result = this.restTemplate.exchange("http://OLX-GATEWAY/olx/token/validate",
				HttpMethod.GET, entity, Boolean.class);
		return result.getBody();
	}

}
