package com.monteirosltda.api.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.monteirosltda.api.dto.TaskInputDTO;
import com.monteirosltda.api.dto.TaskOutputDTO;
import com.monteirosltda.domain.service.TaskService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("task")
@RequiredArgsConstructor
public class TaskController {

    private final TaskService taskService;

    @Operation(description = "Cria nova Tarefa", summary = "Cadastra as tarefas cadastradas", responses = {
            @ApiResponse(description = "Success", responseCode = "200"),
            @ApiResponse(description = "Não Altorizado / Invalid Token", responseCode = "403") }

    )
    @PostMapping
    @PreAuthorize("hasAuthority('admin:create') OR hasAuthority('management:create')")
    public ResponseEntity<TaskOutputDTO> createTask(@RequestBody TaskInputDTO taskInput) {
        return ResponseEntity.ok().body(taskService.save(taskInput));
    }

    @Operation(description = "Listar Tarefas", summary = "Lista as tarefas cadastradas", responses = {
            @ApiResponse(description = "Success", responseCode = "200"),
            @ApiResponse(description = "Não ha tarefas disponiveis", responseCode = "204"),
            @ApiResponse(description = "Não Altorizado / Invalid Token", responseCode = "403") }

    )
    @GetMapping
    public ResponseEntity<List<TaskOutputDTO>> getTasks() {
        List<TaskOutputDTO> result = taskService.findAll();

        if (result.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok().body(result);
    }

    @Operation(description = "Finalizar tarefa", summary = "Finaliza a tarefa", responses = {
            @ApiResponse(description = "Tarefa Concluida com sucesso", responseCode = "200"),
            @ApiResponse(description = "Não Altorizado / Invalid Token", responseCode = "403"),
            @ApiResponse(description = "Error", responseCode = "500") }

    )
    @GetMapping("numero")
    @PreAuthorize("hasAuthority('son:checktask')")
    public ResponseEntity<Map<String, String>> concluirTarefa(@RequestParam(required = true, value = "id") Long id) {
        taskService.concluirTask(id);

        Map<String, String> responseMap = new HashMap<>();
        responseMap.put("message", "Tarefa concluída com sucesso.");

        return ResponseEntity.ok(responseMap);
    }
}
