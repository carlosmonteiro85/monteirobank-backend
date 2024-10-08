package com.monteirosltda.domain.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.monteirosltda.api.dto.CategoriaDto;
import com.monteirosltda.domain.exception.ObjectNotFoundException;
import com.monteirosltda.domain.model.Categoria;
import com.monteirosltda.domain.model.enuns.TipoCategoriaEnum;
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

    public List<Categoria> findAllForType(Integer cod) {
        TipoCategoriaEnum tipoCategoria = TipoCategoriaEnum.findByCod(cod);
        return categoriaRepository.findCategoriasByTipoCategoria(tipoCategoria);
    }

    public Categoria findById(Long idCategoria) {
        return categoriaRepository.findById(idCategoria)
            .orElseThrow(() -> new ObjectNotFoundException("Não foi encontrado uma categoria com o código: " + idCategoria));
    }
}
