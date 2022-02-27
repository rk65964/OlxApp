package com.olx.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.olx.dto.MasterDataDTO;
import com.olx.service.MasterDataService;

public class MasterdataController {
	
	@Autowired
	MasterDataService masterDataService;
	
	@GetMapping(value = "/getMasterData/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<MasterDataDTO> getMasterDataById(@PathVariable("id") int masterDataId) {
		return new ResponseEntity<MasterDataDTO>(masterDataService.getMasterDataById(masterDataId), HttpStatus.OK);

	}
	

}
