package com.example.demo.service.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

import com.example.demo.entity.Message;
import com.example.demo.entity.enumeration.RoleName;
import com.example.demo.service.RoleService;
import com.example.demo.service.dto.request.UserRequestDto;
import com.example.demo.service.mapper.UserMapper;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Role;
import com.example.demo.entity.User;
import com.example.demo.repository.RoleRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class UserServiceImpl implements UserService{
	
	private final UserRepository userRepository;
	
	private final UserMapper userMapper;
	
	private final PasswordEncoder encoder;
	
	private final HttpServletRequest htttpRequest;
	private final KafkaTemplate<String, String> kafkaTemplate;
	@Override
	public boolean existsByEmail(String email) {
		return userRepository.existsByEmail(email);
	}
	
	@Override
	public void addNewUser(UserRequestDto dto) {
		Optional<User> userOpt = userRepository.findByEmail(dto.getEmail());
		if (userOpt.isPresent()) {
			userOpt.get().setName(dto.getName());
			userOpt.get().setPassword(encoder.encode(dto.getPassword()));
			userOpt.get().setPicture(dto.getPicture());
			return;
		}
		Message message = new Message(dto.getEmail(), htttpRequest.getRemoteAddr(), "Signup");
		kafkaTemplate.send("log", message.toString());
		userRepository.save(userMapper.convertToEntity(dto));
	}
	
}
