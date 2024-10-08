package com.monteirosltda.api.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.monteirosltda.api.dto.LancamentoDTO;
import com.monteirosltda.domain.service.LancamentoService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("api/lancamento")
public class LancamentoController {
    
    private final LancamentoService lancamentoService;

    public LancamentoController(LancamentoService lancamentoService) {
        this.lancamentoService = lancamentoService;
    }

    @PostMapping
    public ResponseEntity<Void> criar(@RequestBody LancamentoDTO lancamentoDTO){
        lancamentoService.create(lancamentoDTO);
        log.info("Criando lançamento: " + lancamentoDTO);
        return ResponseEntity.ok().build();
    }
}
