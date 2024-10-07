package com.monteirosltda.domain.model.enuns;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum StatusEnum {
    PENDENTE(1), CONCLUIDA(2);

    private Integer cod;

    public static StatusEnum findByCod(Integer cod) {
        for (StatusEnum tipo : values()) {
            if (tipo.getCod().equals(cod)) {
                return tipo;
            }
        }
        return null;
    }
}
