package com.monteirosltda.domain.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.monteirosltda.api.dto.TaskInputDTO;
import com.monteirosltda.api.dto.TaskOutputDTO;
import com.monteirosltda.domain.exception.NegocioException;
import com.monteirosltda.domain.exception.ObjectNotFoundException;
import com.monteirosltda.domain.model.Task;
import com.monteirosltda.domain.model.TaskConcluida;
import com.monteirosltda.domain.model.User;
import com.monteirosltda.domain.model.enuns.StatusEnum;
import com.monteirosltda.domain.repository.TaskConcluidaRepository;
import com.monteirosltda.domain.repository.TaskRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TaskService {

    private final TaskRepository taskRepository;
    private final TaskConcluidaRepository taskConcluidaRepository;
    private final AuthenticationService authService;
    private final ContaService contaService;

    public TaskOutputDTO save(TaskInputDTO taskInput) {
        Task task = taskRepository.save(taskInput.toEntity());
        return toOutputDTO(task);
    }

    public List<TaskOutputDTO> findAll() {
        return taskRepository.findAll().stream()
                .map(this::toOutputDTO).toList();
    }

    public Task findById(Long id) {
        return taskRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Não foi encontrado uma tarefa com o id: " + id));
    }

    public TaskOutputDTO toOutputDTO(Task task) {
        return TaskOutputDTO.builder()
                .id(task.getId())
                .descricao(task.getDescricao())
                .status(task.getStatus())
                .valor(task.getValor())
                .build();
    }

    public void concluirTask(Long id) {
        User userLogged = authService.getLoggedInUser();
        Task typeTask = findById(id);

        if( Objects.equals(StatusEnum.CONCLUIDA, typeTask.getStatus())){
            throw new NegocioException("Essa tarefa já foi realizada.");
        }
        
        TaskConcluida taskConcluida = gerarTaskConcluida(typeTask, userLogged);
        atualizarSaldoEConcluirTarefa(taskConcluida, userLogged);
    }
    
    private void atualizarSaldoEConcluirTarefa(TaskConcluida taskConcluida, User userLogged) {
        BigDecimal valorTarefa = taskConcluida.getValorPago();
        contaService.adicionarValorSaldoBloqueado(valorTarefa, userLogged.getEmail());
        
        taskConcluida.getTaskRelacionada().setStatus(StatusEnum.CONCLUIDA);
        taskConcluidaRepository.save(taskConcluida);
    }

    private TaskConcluida gerarTaskConcluida(Task task, User user){
        return TaskConcluida.builder()
            .taskRelacionada(task)
            .user(user)
            .valorPago(task.getValor())
        .build();
    }

    // Reforna o status pendente todos os dias as 23h01
    @Scheduled(cron = "0 1 23 * * ?")
    public void resetarTarefas() {
       List<Task> tarefas = taskRepository.findAll();
       tarefas.forEach(t -> t.setStatus(StatusEnum.PENDENTE));
       taskRepository.saveAll(tarefas);
    }
}
