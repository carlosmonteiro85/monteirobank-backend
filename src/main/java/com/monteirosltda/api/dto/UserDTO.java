package com.monteirosltda.api.dto;

import com.monteirosltda.domain.model.User;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    private Integer id;
    private String nome;
    private String username;
    private String role;

    public static UserDTO toDto(User user){
        return UserDTO.builder()
            .id(user.getId())
            .nome(user.getFirstname() + " " + user.getLastname())
            .username(user.getEmail())
            .role(user.getRole().name())
        .build();
    }
}
