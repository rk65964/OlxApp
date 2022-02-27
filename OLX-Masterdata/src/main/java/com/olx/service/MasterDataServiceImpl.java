package com.olx.service;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.olx.dto.MasterDataDTO;
import com.olx.entity.MasterDataEntity;
import com.olx.repo.MasterDataRepository;


@Service
public class MasterDataServiceImpl implements MasterDataService {
	
	@Autowired
	MasterDataRepository  masterDataRepository;
	
	@Autowired
	ModelMapper modelMapper;

	@Override
	public MasterDataDTO getMasterDataById(int masterDataId) {
		Optional<MasterDataEntity> opEntity = masterDataRepository.findById(masterDataId);
        if(opEntity.isPresent()) {
        	MasterDataEntity masterDataEntity = opEntity.get();
        	MasterDataDTO masterDataDTO = convertMasterDataEntityIntoMasterDataDTO(masterDataEntity);
        	return masterDataDTO;
        }
		return null;
	}
	
	private MasterDataDTO convertMasterDataEntityIntoMasterDataDTO(MasterDataEntity mDataEntity) {

		TypeMap<MasterDataEntity, MasterDataDTO> typeMap = this.modelMapper.typeMap(MasterDataEntity.class, MasterDataDTO.class);
		typeMap.addMappings(mapper -> {
			mapper.map(source -> source.getName(), MasterDataDTO::setName);
		});
		MasterDataDTO masterDataDTO = this.modelMapper.map(mDataEntity, MasterDataDTO.class);
		return masterDataDTO;
	}

	private MasterDataEntity convertMasterDataDTOIntoMasterDataEntity(MasterDataDTO mDataDTO) {
		MasterDataEntity masterDataEntity = this.modelMapper.map(mDataDTO, MasterDataEntity.class);
		return masterDataEntity;
	}

}
