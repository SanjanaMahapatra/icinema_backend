package com.infy.auth.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.infy.auth.dto.UserDTO;
import com.infy.auth.dto.UserRegisterDTO;
import com.infy.auth.dto.UserResponseDTO;
import com.infy.auth.exception.AuthenticationException;
import com.infy.auth.service.AuthenticationService;
import com.infy.auth.utility.JwtUtil;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/user")
public class AuthenticationAPI {

	@Autowired
	private AuthenticationService authenticationService;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private JwtUtil jwtUtil;
	
	@Value("${jwt.expiration}")
	private Long expirationTime;
	
	@Autowired
	Environment environment;
	
	@PostMapping("/register")
	public ResponseEntity<String> saveUser(@Valid @RequestBody UserRegisterDTO userRegisterDTO) throws AuthenticationException {
		Integer userId = authenticationService.registerUser(userRegisterDTO);
		String successMessage = environment.getProperty("AuthService.USER_CREATED")+": " + userId;			
		return new ResponseEntity<>(successMessage, HttpStatus.CREATED);
	}
	
	@PostMapping("/login")
	public ResponseEntity<UserResponseDTO> loginUser(@Valid @RequestBody UserDTO userDTO)
	{
		authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(
						userDTO.getUsername(), 
						userDTO.getPassword()
						)
				);
		UserResponseDTO userResponseDTO = new UserResponseDTO();
		String token=jwtUtil.generateToken(userDTO.getUsername());
		userResponseDTO.setToken(token);
		userResponseDTO.setUsername(userDTO.getUsername());
		userResponseDTO.setMessage(environment.getProperty("AuthService.AUTHENTICATION_SUCCESSFUL"));
		userResponseDTO.setExpiresIn(expirationTime);
		userResponseDTO.setToken_type("Bearer");
		return new ResponseEntity<>(userResponseDTO, HttpStatus.OK);
	}
	
	@GetMapping("/test")
	public ResponseEntity<String> testingAuth(){
		return new ResponseEntity<String>("Authentication Working Fine", HttpStatus.OK);
	}
}
