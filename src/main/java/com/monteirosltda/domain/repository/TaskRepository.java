package com.monteirosltda.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.monteirosltda.domain.model.Task;

public interface TaskRepository extends JpaRepository<Task, Long>{
}
