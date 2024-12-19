package com.infy.auth.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserResponseDTO {

	private String token;
	private String username;
	private String message;
	private Long expiresIn;
	private String token_type;
}
