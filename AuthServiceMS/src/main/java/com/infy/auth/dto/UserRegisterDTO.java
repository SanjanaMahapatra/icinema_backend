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
public class UserRegisterDTO {
	@NotNull(message = "{username.absent}")
	@Size(min = 3, max = 20, message = "{username.size.invalid}")
	@Pattern(regexp = "^[a-zA-Z0-9_]+$", message = "{username.pattern.invalid}")
	private String username;
	
	@NotNull(message = "{password.absent}")
	@Size(min = 8, max = 20, message = "{password.size.invalid}")
	@Pattern(regexp = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,20}$", message = "{password.pattern.invalid}")
	private String password;
	
	@NotNull(message = "{email.absent}")
	@Pattern(regexp = "^([\\w.%+-]+)@([\\w-]+\\.)+([\\w]{2,})$", message = "{email.pattern.invalid}")
	private String email;
	
	@NotNull(message = "{phone.absent}")
	@Pattern(regexp = "^[6-9][0-9]{9}$", message = "{phone.pattern.invalid}")
	private String phone;
}
