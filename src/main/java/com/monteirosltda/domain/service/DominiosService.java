package com.monteirosltda.domain.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.monteirosltda.api.dto.CategoriaDto;
import com.monteirosltda.domain.model.Categoria;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DominiosService {

    private final CategoriaService categoriaService;

    public List<CategoriaDto> getCategorias(Integer cod) {
        return categoriaService.findAllForType(cod).stream().map(Categoria::toDto).toList();
    }

}
