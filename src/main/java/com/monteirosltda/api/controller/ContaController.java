package com.monteirosltda.api.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.monteirosltda.api.dto.ContaOutputDTO;
import com.monteirosltda.domain.service.ContaService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("conta")
@RequiredArgsConstructor
public class ContaController {

    private final ContaService contaService;

    @Operation(description = "Saldo Conta", summary = "Verificar Saldo", responses = {
            @ApiResponse(description = "Sucesso", responseCode = "200"),
            @ApiResponse(description = "Não Autorizado / Invalid Token", responseCode = "403") })
    @GetMapping("meu-saldo")
    @PreAuthorize("hasAuthority('son:checkpay') OR hasAuthority('admin:read') OR hasAuthority('management:read')")
    public ResponseEntity<ContaOutputDTO> getSaldoUserLogged() {
        return ResponseEntity.ok(contaService.getSaldoContaUserLogged());
    }

    @Operation(description = "Finalizar tarefa", summary = "Finaliza a tarefa", responses = {
            @ApiResponse(description = "Tarefa Concluida com sucesso", responseCode = "200"),
            @ApiResponse(description = "Não Altorizado / Invalid Token", responseCode = "403"),
            @ApiResponse(description = "Error", responseCode = "500") })
    @GetMapping("user")
    @PreAuthorize("hasAuthority('admin:update')")
    public ResponseEntity<Map<String, String>> concluirTarefa(
            @RequestParam(required = true, value = "userName") String userName) {
        contaService.paymentUser(userName);
        Map<String, String> responseMap = new HashMap<>();
        responseMap.put("message", "Saldo Pago com sucesso");
        return ResponseEntity.ok(responseMap);
    }
}
