package com.monteirosltda.core.initialcharge;

import java.util.Arrays;

import java.util.List;

import com.monteirosltda.api.dto.RegisterRequest;
import com.monteirosltda.domain.model.Conta;
import com.monteirosltda.domain.model.User;
import com.monteirosltda.domain.model.enuns.Role;

public class DbStub {

    private DbStub() {
        throw new IllegalStateException("Utility class");
      }

    public static List<RegisterRequest> getUsers() {
        return Arrays.asList(
            RegisterRequest.builder()
                .firstname("Carlos")
                .lastname("Monteiro")
                .email("carlos.monteiro")
                .password("aqd791222")
                .role(Role.ADMIN)
            .build(),
            RegisterRequest.builder()
                .firstname("Dayane")
                .lastname("Monteiro")
                .email("dayane.monteiro")
                .password("aqd791222")
                .role(Role.MANAGER)
            .build(),
            RegisterRequest.builder()
                .firstname("Enzo")
                .lastname("Monteiro")
                .email("enzo.monteiro")
                .password("791222")
                .role(Role.SON)
            .build(),
            RegisterRequest.builder()
                .firstname("Ana")
                .lastname("Carla Monteiro")
                .email("ana.monteiro")
                .password("791222")
                .role(Role.SON)
            .build()
        );
    }

    public static Conta criateConta(User user){
        return Conta.builder()
            .user(user)
        .build();
    }
}
