package com.olx.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.olx.dto.AdvertiseDTO;
import com.olx.service.AdvertiseService;

@RestController
@CrossOrigin
@RequestMapping("/olx-adv")
public class AdvertiseController {

	@Autowired
	AdvertiseService advertiseService;

	@PostMapping(value = "/avertise", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<AdvertiseDTO> getAllAdvertise() {
		return advertiseService.getAllAdvertise();
	}
	
	@PostMapping(value = "/avertise/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public AdvertiseDTO getAdvertisesById(@PathVariable("id") int username) {
		return advertiseService.getAdvertisesById(username);
	}

	@PostMapping(value = "/createaAvertise", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<AdvertiseDTO> createAdvertise(@RequestBody AdvertiseDTO advertiseDTO, @RequestHeader("Authorization") String authToken) {
		return new ResponseEntity<AdvertiseDTO>(advertiseService.createaAvertise(advertiseDTO, authToken), HttpStatus.CREATED);
	}

	@PutMapping(value = "/updateAdvertise/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<AdvertiseDTO> updateAdvertise(@RequestBody AdvertiseDTO advertiseDTO,
			@PathVariable("id") int advertiseId) {
		return new ResponseEntity<AdvertiseDTO>(advertiseService.updateAdvertise(advertiseDTO, advertiseId),
				HttpStatus.OK);
	}

}
