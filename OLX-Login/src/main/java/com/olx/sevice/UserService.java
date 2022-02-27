package com.olx.sevice;

import com.olx.dto.AuthenticationRequest;

public interface UserService {

	public AuthenticationRequest createUser(AuthenticationRequest authenticationRequest);

	public AuthenticationRequest getUserkById(int userId);

	public boolean userLogout(String authToken);

}
