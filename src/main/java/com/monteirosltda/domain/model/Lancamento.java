package com.monteirosltda.domain.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

import com.monteirosltda.api.dto.LancamentoDTO;
import com.monteirosltda.domain.model.enuns.StatusEnum;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "lancamento")
public class Lancamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "data_cadastro")
    private LocalDate dataCadastro; 

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="categoria_id")
    private Categoria categoria;

    @Column(name = "descricao")
    private String descricao;

    @Column(name = "valor")
    private BigDecimal valor;

    @Column(name = "pago_em")
    private LocalDate pagoEm;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private StatusEnum status;

    public static LancamentoDTO toDto(Lancamento lancamento){
        LancamentoDTO dto = new LancamentoDTO();
        dto.setId(lancamento.getId());
        dto.setDataCadastro(lancamento.getDataCadastro());
        dto.setIdCategoria(null);
        dto.setDescricao(lancamento.getDescricao());
        dto.setValor(lancamento.getValor());
        dto.setPagoEm(lancamento.getPagoEm());
        dto.setCodStatus(lancamento.getStatus().getCod());
        return dto;
    }

    public static Lancamento toEntity(LancamentoDTO dto, Categoria categoria){
        Lancamento lancamento = new Lancamento();
        lancamento.setId(Objects.nonNull(dto.getId()) ? dto.getId() : null);
        lancamento.setDataCadastro(dto.getDataCadastro());
        lancamento.setCategoria(categoria);
        lancamento.setDescricao(dto.getDescricao());
        lancamento.setValor(dto.getValor());
        lancamento.setPagoEm(dto.getPagoEm());
        lancamento.setStatus(StatusEnum.findByCod(dto.getCodStatus()));
        return lancamento;
    }
}
