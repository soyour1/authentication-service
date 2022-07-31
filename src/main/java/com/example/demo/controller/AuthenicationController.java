package com.example.demo.controller;

import com.example.demo.service.AuthenticationService;
import com.example.demo.service.UserService;
import com.example.demo.service.dto.request.LoginRequestDto;
import com.example.demo.service.dto.request.UserRequestDto;
import com.example.demo.service.dto.response.LoginResponseDto;
import com.example.demo.service.dto.response.ResponseMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@Validated
public class AuthenicationController {

	private final UserService userService;

	private final AuthenticationService authenticationService;
	@PostMapping("/signup")
	public ResponseEntity<?> addUser(@Valid @RequestBody UserRequestDto dto){
		if(userService.existsByEmail(dto.getEmail())){
			return new ResponseEntity<>(new ResponseMessage("Email is existed"), HttpStatus.OK);
		}
		userService.addNewUser(dto);
		return new ResponseEntity<>(new ResponseMessage("Create user success"), HttpStatus.OK);
	}

	@PostMapping("/signin")
	public ResponseEntity<LoginResponseDto> login(@Valid @RequestBody LoginRequestDto dto) throws Exception {
		return ResponseEntity.ok(authenticationService.login(dto));
	}

	@PostMapping("/logout")
	public ResponseEntity<?> logout(@RequestHeader HttpHeaders headers) {
		authenticationService.logout(headers);
		return ResponseEntity.noContent().build();
	}
}
