package com.monteirosltda.api.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.monteirosltda.api.dto.CategoriaDto;
import com.monteirosltda.api.dto.LancamentoDTO;
import com.monteirosltda.domain.service.CategoriaService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("api/lancamento")
public class LancamentoController {
    
    // private final CategoriaService service;

    // public CategoriaController(CategoriaService service) {
    //     this.service = service;
    // }

    @PostMapping
    public ResponseEntity<Void> criar(@RequestBody LancamentoDTO lancamentoDTO){
        // service.save(categotia);
        log.info("" + lancamentoDTO);
        return ResponseEntity.ok().build();
    }
}
