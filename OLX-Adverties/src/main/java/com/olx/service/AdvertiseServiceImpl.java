package com.olx.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.olx.dto.AdvertiseDTO;
import com.olx.dto.Category;
import com.olx.entity.AdvertiseEntity;
import com.olx.exception.InvalidStockIdException;
import com.olx.exception.InvalidTokenException;
import com.olx.repo.AdvertiseRepository;

@Service
public class AdvertiseServiceImpl implements AdvertiseService {

	@Autowired
	AdvertiseRepository advertiseRepository;

	@Autowired
	ModelMapper modelMapper;
	
	@Autowired
	MasterDataServiceDelegate masterdataServiceDelegate;
	
	@Autowired
	LoginServiceDelegateImpl loginServiceDelegate;

	@Override
	public AdvertiseDTO createaAvertise(AdvertiseDTO advertiseDTO, String authToken) {
		if(loginServiceDelegate.isValidUser(authToken)==false) {
			throw new InvalidTokenException(authToken);
		}
		
		Category category = masterdataServiceDelegate.getCategoryById(""+advertiseDTO.getCategoryId());
		System.out.println("category = " + category);
		//store advertise into database
		
		AdvertiseEntity stockEntity = convertAdvertiseDTOIntoAdvertiseEntity(advertiseDTO);
		stockEntity = advertiseRepository.save(stockEntity);
		advertiseDTO.setId(stockEntity.getId());
		return advertiseDTO;

	}

	@Override
	public AdvertiseDTO updateAdvertise(AdvertiseDTO advertiseDTO, int advertiseId) {
		Optional<AdvertiseEntity> opStockEntity = advertiseRepository.findById(advertiseId);
		if (opStockEntity.isPresent()) {
			AdvertiseEntity advertiseEntity = opStockEntity.get();
			advertiseEntity = convertAdvertiseDTOIntoAdvertiseEntity(advertiseDTO);
			advertiseEntity.setId(advertiseId);
			advertiseEntity = advertiseRepository.save(advertiseEntity);
			advertiseDTO.setId(advertiseEntity.getId());
			advertiseRepository.delete(advertiseEntity);
			return advertiseDTO;
		}
		return null;
	}

	@Override
	public List<AdvertiseDTO> getAllAdvertise() {
		List<AdvertiseEntity> stockEntities = advertiseRepository.findAll();
		List<AdvertiseDTO> stockDtoList = new ArrayList<AdvertiseDTO>();
		for (AdvertiseEntity stockEntity : stockEntities) {
			AdvertiseDTO stock = convertAdvertiseEntityIntoAdvertiseDTO(stockEntity);
			stockDtoList.add(stock);
		}
		return stockDtoList;
	}

	@Override
	public AdvertiseDTO getAdvertisesById(int id) {
		Optional<AdvertiseEntity> opStockEntity = advertiseRepository.findById(id);
		if (opStockEntity.isPresent()) {
			AdvertiseEntity stockEntity = opStockEntity.get();
			AdvertiseDTO stock = convertAdvertiseEntityIntoAdvertiseDTO(stockEntity);
			return stock;
		}
		throw new InvalidStockIdException(""+ id);
	}
	// conversion
	private AdvertiseDTO convertAdvertiseEntityIntoAdvertiseDTO(AdvertiseEntity aEntity) {

		TypeMap<AdvertiseEntity, AdvertiseDTO> typeMap = this.modelMapper.typeMap(AdvertiseEntity.class,
				AdvertiseDTO.class);
		typeMap.addMappings(mapper -> {
			mapper.map(source -> source.getUserName(), AdvertiseDTO::setUserName);
		});
		AdvertiseDTO advertiseDTO = this.modelMapper.map(aEntity, AdvertiseDTO.class);
		return advertiseDTO;
	}

	private AdvertiseEntity convertAdvertiseDTOIntoAdvertiseEntity(AdvertiseDTO adDto) {

		TypeMap<AdvertiseDTO, AdvertiseEntity> typeMap = this.modelMapper.typeMap(AdvertiseDTO.class,
				AdvertiseEntity.class);
		typeMap.addMappings(mapper -> {
			mapper.map(source -> source.getUserName(), AdvertiseEntity::setUserName);
		});
		AdvertiseEntity advertiseEntity = this.modelMapper.map(adDto, AdvertiseEntity.class);
		return advertiseEntity;
	}



}
