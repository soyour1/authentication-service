package com.example.demo.service.mapper;

import com.example.demo.entity.Role;
import com.example.demo.entity.User;
import com.example.demo.entity.enumeration.RoleName;
import com.example.demo.service.RoleService;
import com.example.demo.service.dto.request.UserRequestDto;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Service
@AllArgsConstructor
public class UserMapper {
	private final RoleMapper roleMapper;
	
	private final PasswordEncoder encoder;
	public User convertToEntity(UserRequestDto dto){
	
		
		Role role = new Role();
		role.setRoleName(RoleName.ADMIN);
		
		
		User user = new User();
		user.setEmail(dto.getEmail());
		user.setName(dto.getName());
		user.setPassword(encoder.encode(dto.getPassword()));
		user.setPicture(dto.getPicture());
		dto.getRoles().stream().forEach(project -> user.addRole(roleMapper.convertEntity(project)));
		return user;
	}
}
