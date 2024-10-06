package com.monteirosltda.domain.service;

import org.springframework.stereotype.Service;

import com.monteirosltda.api.dto.CategoriaDto;
import com.monteirosltda.domain.model.Categoria;
import com.monteirosltda.domain.repository.CategoriaRepository;

@Service
public class CategoriaService {

    private final CategoriaRepository categoriaRepository;

    public CategoriaService(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }

    public void save(CategoriaDto dto) {
        Categoria categoria = Categoria.toEntity(dto);
        categoriaRepository.save(categoria);
    }
}
