package com.example.demo.service.dto.request;

import com.example.demo.entity.Role;
import lombok.Builder;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Set;

@Data
@Builder
public class UserRequestDto {
	
	@NotBlank(message = "Name is required")
	private String name;
	
	@NotBlank(message = "Email is required")
	private String email;
	
	@NotBlank(message = "Password is required")
	private String password;
	
	@NotBlank(message = "Picture is required")
	private String picture;
	
	@Valid
	@NotEmpty
	private List<Role> roles;
}
