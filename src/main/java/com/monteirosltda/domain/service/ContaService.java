package com.monteirosltda.domain.service;

import java.math.BigDecimal;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.monteirosltda.api.dto.ContaOutputDTO;
import com.monteirosltda.domain.exception.ObjectNotFoundException;
import com.monteirosltda.domain.model.Conta;
import com.monteirosltda.domain.model.User;
import com.monteirosltda.domain.repository.ContaRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ContaService {

    private final ContaRepository contaRepository;
    private final AuthenticationService authenticationService;

    public Conta findByContaForTitular(String titular) {
        return contaRepository.findByContaForTitular(titular)
            .orElseThrow(() -> new ObjectNotFoundException("Não foi encontrada uma conta relacionado ao usuario " + titular));
    }

    public BigDecimal adicionarValorSaldoBloqueado(BigDecimal valorTarefa, String titular) {
        Conta conta = findByContaForTitular(titular);
        BigDecimal valorBloqueado = conta.getSaldoBloqueado().add(valorTarefa);
        conta.setSaldoBloqueado(valorBloqueado);
        return contaRepository.save(conta).getSaldoBloqueado();
    }
    
    @Scheduled(cron = "0 0 23 ? * FRI")
    public void liberatePayments() {
        contaRepository.findAll().forEach(this::liberatePaymentForConta);
    }

    private void liberatePaymentForConta(Conta conta) {
        BigDecimal valorLiberado = conta.getSaldoBloqueado().add(conta.getSaldoLiberado());
        conta.setSaldoLiberado(valorLiberado);
        conta.setSaldoBloqueado(BigDecimal.ZERO);
        contaRepository.save(conta);
    }

    public void paymentUser(String titular) {
        Conta conta = findByContaForTitular(titular);
        conta.setSaldoLiberado(BigDecimal.ZERO);
        contaRepository.save(conta);
    }

    public ContaOutputDTO getSaldoContaUserLogged(){
        User userLogged = authenticationService.getLoggedInUser();
       return ContaOutputDTO.toDto(findByContaForTitular(userLogged.getEmail()));     
    }

    public List<ContaOutputDTO> getHistorySaldo(){
        return contaRepository.findAll().stream()
            .map(ContaOutputDTO::toDto).toList();
    }
}
