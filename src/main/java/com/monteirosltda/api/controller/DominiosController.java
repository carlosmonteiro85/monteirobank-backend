package com.monteirosltda.api.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.monteirosltda.api.dto.CategoriaDto;
import com.monteirosltda.domain.service.DominiosService;

import lombok.RequiredArgsConstructor;


@RestController
@RequestMapping("api/dominios")
@RequiredArgsConstructor
public class DominiosController {

	private final DominiosService dominiosService;

	@GetMapping("/{cod}")
	public ResponseEntity<List<CategoriaDto>> getCategorias(@PathVariable("cod") Integer cod) {
		List<CategoriaDto> dominiosCategoria = dominiosService.getCategorias(cod);
		if(dominiosCategoria.isEmpty()){
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(dominiosCategoria); 
	}

	@GetMapping("/versao")
	public String getVersao() {
		return dominiosService.getVersao();
	}
}
