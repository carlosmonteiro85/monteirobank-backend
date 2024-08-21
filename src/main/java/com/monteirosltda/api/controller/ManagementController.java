package com.monteirosltda.api.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.monteirosltda.api.dto.UserDTO;
import com.monteirosltda.domain.service.AuthenticationService;

@RestController
@RequestMapping("/api/v1/management")
@Tag(name = "Management")
@RequiredArgsConstructor
public class ManagementController {

	private final AuthenticationService service;

	@PostMapping("/userlogged")
	public ResponseEntity<UserDTO> getUserLogged() {
		return ResponseEntity.ok().body(service.toUserDTO());
	}
}
