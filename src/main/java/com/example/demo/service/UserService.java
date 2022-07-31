package com.example.demo.service;

import java.util.List;

import com.example.demo.entity.Role;
import com.example.demo.entity.User;
import com.example.demo.service.dto.request.UserRequestDto;

public interface UserService {
	boolean existsByEmail(String email);
	
	void addNewUser(UserRequestDto dto);
}
