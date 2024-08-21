package com.monteirosltda.api.controller;

import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.monteirosltda.api.dto.ContaOutputDTO;
import com.monteirosltda.domain.service.ContaService;

@RestController
@RequestMapping("admin")
@PreAuthorize("hasRole('ADMIN')") 
@RequiredArgsConstructor
public class AdminController {

	private final ContaService contaService;

	@GetMapping
	@PreAuthorize("hasAuthority('admin:read')") 
	public ResponseEntity<List<ContaOutputDTO>> getHistorySaldo() {
		List<ContaOutputDTO> historicoContas = contaService.getHistorySaldo();
		if(historicoContas.isEmpty()){
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(historicoContas); 
	}
}
