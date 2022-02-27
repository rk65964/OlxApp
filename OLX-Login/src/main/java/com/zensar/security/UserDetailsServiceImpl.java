package com.zensar.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.olx.entity.UserEntity;
import com.olx.repo.UserRepository;


@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		List<UserEntity> userEntities = userRepository.findByUserName(username);
		if(userEntities==null || userEntities.size()==0) {// user not found
			throw new UsernameNotFoundException(username);
		}
		UserEntity userEntity = userEntities.get(0);
		Collection<GrantedAuthority> autherities = new ArrayList<GrantedAuthority>();
		String roles  = userEntity.getRoles();
		String roleArray[] = roles.split(",");
		for(int i= 0;i<roleArray.length;i++) {
			autherities.add(new SimpleGrantedAuthority(roleArray[i]));
		}
		UserDetails userDetails = new User(username,
				passwordEncoder.encode(userEntity.getPassword()), autherities);
		return userDetails;
	}

}
