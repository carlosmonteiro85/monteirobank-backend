package com.monteirosltda.domain.model.enuns;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum TipoCategoriaEnum {
    RECEITA(1), DESPEZA(2);

    private Integer cod;

    public static TipoCategoriaEnum findByCod(Integer cod) {
        for (TipoCategoriaEnum tipo : values()) {
            if (tipo.getCod().equals(cod)) {
                return tipo;
            }
        }
        return null;
    }
}
