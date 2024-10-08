package com.monteirosltda.domain.service;

import org.springframework.stereotype.Service;

import com.monteirosltda.api.dto.LancamentoDTO;
import com.monteirosltda.domain.model.Categoria;
import com.monteirosltda.domain.model.Lancamento;
import com.monteirosltda.domain.repository.LancamentoRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LancamentoService {

    private final LancamentoRepository lancamentoRepository;
    private final CategoriaService categoriaService;

    public void create(LancamentoDTO dto) {
        Categoria categoria = categoriaService.findById(dto.getIdCategoria());
        Lancamento lancamento = Lancamento.toEntity(dto, categoria);
        lancamentoRepository.save(lancamento);
    }

}
