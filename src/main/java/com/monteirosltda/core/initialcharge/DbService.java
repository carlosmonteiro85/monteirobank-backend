package com.monteirosltda.core.initialcharge;

import java.util.Optional;

import org.springframework.stereotype.Component;

import com.monteirosltda.domain.model.Conta;
import com.monteirosltda.domain.model.User;
import com.monteirosltda.domain.repository.ContaRepository;
import com.monteirosltda.domain.repository.UserRepository;
import com.monteirosltda.domain.service.AuthenticationService;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class DbService {

    private final UserRepository userRepository;
    private final AuthenticationService authenticationService;
    private final ContaRepository contaRepository;
	
	public void carregarCargaInicialFile() {
		
        DbStub.getUsers().forEach(authenticationService::register);

        Optional<User> user1 = userRepository.findByEmail("enzo.monteiro");
        if(user1.isPresent()){
            Conta conta1 = DbStub.criateConta(user1.get());
            contaRepository.save(conta1);
            user1.get().setConta(conta1);
            userRepository.save(user1.get());
        }
        
        Optional<User> user2 = userRepository.findByEmail("ana.monteiro");
        if(user2.isPresent()){
            Conta conta2 = DbStub.criateConta(user2.get());
            contaRepository.save(conta2);
            user2.get().setConta(conta2);
            userRepository.save(user2.get());
        }
	}
}

