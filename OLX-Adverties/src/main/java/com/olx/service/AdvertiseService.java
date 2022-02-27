package com.olx.service;

import java.util.List;

import com.olx.dto.AdvertiseDTO;

public interface AdvertiseService {


	public AdvertiseDTO updateAdvertise(AdvertiseDTO advertiseDTO, int advertiseId);

	AdvertiseDTO createaAvertise(AdvertiseDTO advertiseDTO, String authToken);

	public List<AdvertiseDTO> getAllAdvertise();

	public AdvertiseDTO getAdvertisesById(int id);

}
