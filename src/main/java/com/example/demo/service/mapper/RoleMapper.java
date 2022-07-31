package com.example.demo.service.mapper;

import com.example.demo.entity.Role;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RoleMapper {
	public Role convertEntity(Role dto) {
		
		Role role = new Role();
		
		role.setRoleName(dto.getRoleName());
		
		return role;
	}
}
