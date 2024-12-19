package com.infy.auth.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RestController;

import com.infy.auth.dto.UserDTO;
import com.infy.auth.dto.UserRegisterDTO;
import com.infy.auth.entity.User;
import com.infy.auth.exception.AuthenticationException;
import com.infy.auth.repository.AuthenticationRepository;

@RestController
@Transactional
public class AuthenticationServiceImpl implements AuthenticationService, UserDetailsService {

	@Autowired
	private AuthenticationRepository authenticationRepository;
	
	@Autowired
	BCryptPasswordEncoder passwordEncoder;
	
	@Autowired
	ModelMapper modelMapper;
	
	@Override
	public Integer registerUser(UserRegisterDTO userRegisterDTO) throws AuthenticationException{

		Optional<User> optional = authenticationRepository.findByUsername(userRegisterDTO.getUsername());
		if(optional.isPresent()) {
			throw new AuthenticationException("AuthService.USER_ALREADY_EXIST");
		}
		userRegisterDTO.setPassword(passwordEncoder.encode(userRegisterDTO.getPassword()));
		User userEntity = modelMapper.map(userRegisterDTO, User.class);
		return authenticationRepository.save(userEntity).getUserId();
	}

	@Override
	public UserDTO findByUsername(String username) {
		Optional<User> user = authenticationRepository.findByUsername(username);
		if (user.isPresent())
			return modelMapper.map(user, UserDTO.class);
		return null;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserDTO userDTO = findByUsername(username);
		if(userDTO == null) {
			throw new UsernameNotFoundException("User name " + username + " not found!");
		}
		List<GrantedAuthority> authorities = new ArrayList<>();
		return new org.springframework.security.core.userdetails.User(username, userDTO.getPassword(), authorities);
	}

	
	
	
}
