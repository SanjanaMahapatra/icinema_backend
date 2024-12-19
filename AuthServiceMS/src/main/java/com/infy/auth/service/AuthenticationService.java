package com.infy.auth.service;

import com.infy.auth.dto.UserDTO;
import com.infy.auth.dto.UserRegisterDTO;
import com.infy.auth.exception.AuthenticationException;

public interface AuthenticationService {
	
	public Integer registerUser(UserRegisterDTO userRegisterDTO) throws AuthenticationException;
	public UserDTO findByUsername(String username);
}
