package com.monteirosltda.domain.model;

import java.math.BigDecimal;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "task_concluidas")
public class TaskConcluida {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "valor_pago", nullable = false)
    private BigDecimal valorPago;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "task_id", nullable = false)
    private Task taskRelacionada;

    @ManyToOne()
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
}
