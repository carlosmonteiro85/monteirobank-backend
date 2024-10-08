package com.monteirosltda.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.monteirosltda.api.dto.CategoriaDto;
import com.monteirosltda.domain.model.Categoria;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DominiosService {

    private final CategoriaService categoriaService;

    @Value("${app.version}")
    private String version; 

    public List<CategoriaDto> getCategorias(Integer cod) {
        return categoriaService.findAllForType(cod).stream().map(Categoria::toDto).toList();
    }

    public String getVersao() {
        return version; 
    }
}
