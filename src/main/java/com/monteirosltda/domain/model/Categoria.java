package com.monteirosltda.domain.model;

import com.monteirosltda.api.dto.CategoriaDto;
import com.monteirosltda.domain.model.enuns.TipoCategoriaEnum;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "categoria")
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String descricao;

    @Column(name = "tipo_categoria")
    @Enumerated(EnumType.STRING)
    private TipoCategoriaEnum tipoCategoria;

    public static Categoria toEntity(CategoriaDto dto){
        Categoria categoria = new Categoria();
        categoria.setDescricao(dto.getDescricao());
        categoria.setTipoCategoria(TipoCategoriaEnum.findByCod(dto.getTipoCategoria()));
        return categoria;
    }

    public static CategoriaDto toDto(Categoria categoria){
        CategoriaDto dto = new CategoriaDto();
        dto.setId(categoria.getId());
        dto.setDescricao(categoria.getDescricao());
        dto.setTipoCategoria(categoria.getTipoCategoria().getCod());
        return dto;
    }
}
