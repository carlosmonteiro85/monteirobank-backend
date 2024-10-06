package com.monteirosltda.api.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;


@RestController
@RequestMapping("admin")
@PreAuthorize("hasRole('ADMIN')") 
@RequiredArgsConstructor
public class AdminController {


	// @GetMapping
	// @PreAuthorize("hasAuthority('admin:read')") 
	// public ResponseEntity<List<ContaOutputDTO>> getHistorySaldo() {
	// 	List<ContaOutputDTO> historicoContas = contaService.getHistorySaldo();
	// 	if(historicoContas.isEmpty()){
	// 		return ResponseEntity.noContent().build();
	// 	}
	// 	return ResponseEntity.ok(historicoContas); 
	// }
}
