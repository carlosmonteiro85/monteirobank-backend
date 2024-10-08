package com.monteirosltda.domain.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

import com.monteirosltda.api.dto.LancamentoDTO;
import com.monteirosltda.api.dto.LancamentoListItemDTO;
import com.monteirosltda.domain.exception.ObjectNotFoundException;
import com.monteirosltda.domain.model.Categoria;
import com.monteirosltda.domain.model.Lancamento;
import com.monteirosltda.domain.model.enuns.TipoCategoriaEnum;
import com.monteirosltda.domain.model.util.Utilitarios;
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

    public List<LancamentoListItemDTO> findByTipoCategoria(Integer cod) {
         List<LancamentoListItemDTO> result = lancamentoRepository.findCategoriasByTipoCategoria(TipoCategoriaEnum.findByCod(cod))
                .stream().map(Lancamento::toDtoItemList)
                .toList();

      result.forEach(l -> l.setImpactoReceita(getImpactoOrcamento(l.getId())));
      
      return result;
    }

    public Lancamento findById(Long id){
        return lancamentoRepository.findById(id)
            .orElseThrow(() -> new ObjectNotFoundException("Não foi encontrado um lançamento com o id:" + id));
    }

    public BigDecimal getImpactoOrcamento(Long id) {
        Lancamento lancamento = findById(id);
        
        int mes = lancamento.getDataCadastro().getMonthValue();
        int ano = lancamento.getDataCadastro().getYear();

        TipoCategoriaEnum tipoCategoria = TipoCategoriaEnum.RECEITA.equals(lancamento.getCategoria().getTipoCategoria()) ? TipoCategoriaEnum.DESPEZA : TipoCategoriaEnum.RECEITA ;
        
        BigDecimal somaMes = lancamentoRepository.somarValoresPorMesEAno(mes, ano, tipoCategoria);

        // Se a soma do mês for zero, o impacto é 0 para evitar divisão por zero
        if (somaMes == null || BigDecimal.ZERO.compareTo(somaMes) == 0) {
            return BigDecimal.ZERO;
        }

        BigDecimal valorLancamento = lancamento.getValor();

        // Calculando o impacto percentual: (valorLancamento / somaMes) * 100
        BigDecimal impactoPercentual = valorLancamento
            .divide(somaMes, 4, RoundingMode.HALF_UP)
            .multiply(BigDecimal.valueOf(100));

        return impactoPercentual;
    }

    public String getTotalLancamentosMensais(Integer codTipoCategoria) {
        int mesAtual = LocalDate.now().getMonthValue();
        int anoAtual = LocalDate.now().getYear();
        TipoCategoriaEnum tipoCategoriaEnum = TipoCategoriaEnum.findByCod(codTipoCategoria);
        return Utilitarios.formataValor(lancamentoRepository.somarValoresPorMesEAno(mesAtual, anoAtual, tipoCategoriaEnum));
    }

    public String getTotalLancamentosAnual(Integer codTipoCategoria) {
        int anoAtual = LocalDate.now().getYear();
        TipoCategoriaEnum tipoCategoriaEnum = TipoCategoriaEnum.findByCod(codTipoCategoria);
        return Utilitarios.formataValor(lancamentoRepository.somarValoresPorAno(anoAtual, tipoCategoriaEnum));
    }

    public String getPorcentagem(Integer codTipoCategoria){
        int mesAtual = LocalDate.now().getMonthValue();
        int anoAtual = LocalDate.now().getYear();

        TipoCategoriaEnum tipoCategoriaEnum = TipoCategoriaEnum.findByCod(codTipoCategoria);

        BigDecimal valorMensal = lancamentoRepository.somarValoresPorMesEAno(mesAtual, anoAtual, tipoCategoriaEnum);
        BigDecimal valorAnual = lancamentoRepository.somarValoresPorAno(anoAtual, tipoCategoriaEnum);


        BigDecimal impactoPercentual = valorAnual
                        .divide(valorMensal, 2, RoundingMode.HALF_UP)
                        .multiply(BigDecimal.valueOf(100));

        return impactoPercentual.toString() + "%";

    }

}
