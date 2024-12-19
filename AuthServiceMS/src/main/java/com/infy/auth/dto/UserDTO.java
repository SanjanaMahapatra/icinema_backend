package com.infy.auth.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {

	@NotNull(message = "{username.absent}")
	@Size(min = 5, max = 20, message = "{username.size.invalid}")
	@Pattern(regexp = "^[a-zA-Z0-9_]+$", message = "{username.pattern.invalid}")
	private String username;
	
	@NotNull(message = "{password.absent}")
	@Size(min = 8, max = 20, message = "{password.size.invalid}")
	@Pattern(regexp = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,20}$", message = "{password.pattern.invalid}")
	private String password;
}
