package com.monteirosltda.api.controller;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.monteirosltda.api.dto.LancamentoDTO;
import com.monteirosltda.api.dto.LancamentoListItemDTO;
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

	@GetMapping("/{cod}")
	public ResponseEntity<List<LancamentoListItemDTO>> findAll(@PathVariable("cod") Integer cod) {
		List<LancamentoListItemDTO> lancamentos = lancamentoService.findByTipoCategoria(cod);
		if(lancamentos.isEmpty()){
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(lancamentos); 
	}

    @GetMapping("impacto/{id}")
	public ResponseEntity<BigDecimal> inpactoReceita(@PathVariable("id") Long cod) {
		 BigDecimal impactoOrcamento = lancamentoService.getImpactoOrcamento(cod);
		return ResponseEntity.ok(impactoOrcamento); 
	}

    @GetMapping("total-lancamentos/{codTipoCategoria}")
	public String getTotalLancamentosMensais(@PathVariable("codTipoCategoria") Integer codTipoCategoria) {
		 return lancamentoService.getTotalLancamentosMensais(codTipoCategoria);
	}

    @GetMapping("total-lancamentos-anual/{codTipoCategoria}")
	public String getTotalLancamentosAnual(@PathVariable("codTipoCategoria") Integer codTipoCategoria) {
		 return lancamentoService.getTotalLancamentosAnual(codTipoCategoria);
	}

    @GetMapping("porcentagens/{codTipoCategoria}")
	public String getPorcentagens(@PathVariable("codTipoCategoria") Integer codTipoCategoria) {
		 return lancamentoService.getPorcentagem(codTipoCategoria);
	}
}
