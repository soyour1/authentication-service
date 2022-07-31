package com.example.demo.service.dto.request;

import com.example.demo.entity.enumeration.RoleName;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
@Builder
public class RoleRequestDto {
	@NotNull(message = "Role name is required")
	private RoleName roleName;
}
