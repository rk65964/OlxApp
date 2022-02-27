package com.olx.sevice;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.olx.dto.AuthenticationRequest;
import com.olx.entity.UserEntity;
import com.olx.repo.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository userRepository;

	
	@Autowired
	ModelMapper modelMapper;

	@Override
	public AuthenticationRequest createUser(AuthenticationRequest authenticationRequest) {
		UserEntity userEntity = convertUserDTOIntoUserEntity(authenticationRequest);
		userEntity = userRepository.save(userEntity);
		authenticationRequest.setId(userEntity.getId());
		return authenticationRequest;
	}

	@Override
	public AuthenticationRequest getUserkById(int userId) {
		Optional<UserEntity> opUserEntity = userRepository.findById(userId);
		if (opUserEntity.isPresent()) {
			UserEntity userEntity = opUserEntity.get();
			AuthenticationRequest request = convertUserEntityIntoUserDTO(userEntity);
			return request;
		}
		return null;
	}

	// conversion
	private AuthenticationRequest convertUserEntityIntoUserDTO(UserEntity userEntity) {

		TypeMap<UserEntity, AuthenticationRequest> typeMap = this.modelMapper.typeMap(UserEntity.class,
				AuthenticationRequest.class);
		typeMap.addMappings(mapper -> {
			mapper.map(source -> source.getFirstName(), AuthenticationRequest::setFirstName);
		});
		AuthenticationRequest authenticationRequest = this.modelMapper.map(userEntity, AuthenticationRequest.class);
		return authenticationRequest;
	}

	private UserEntity convertUserDTOIntoUserEntity(AuthenticationRequest aRequest) {

		TypeMap<AuthenticationRequest, UserEntity> typeMap = this.modelMapper.typeMap(AuthenticationRequest.class,
				UserEntity.class);
		typeMap.addMappings(mapper -> {
			mapper.map(source -> source.getFirstName(), UserEntity::setFirstName);
		});
		UserEntity stockEntity = this.modelMapper.map(aRequest, UserEntity.class);
		return stockEntity;
	}

	@Override
	public boolean userLogout(String authToken) {
		// TODO Auto-generated method stub
		return false;
	}

}
