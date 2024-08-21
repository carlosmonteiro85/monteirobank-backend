package com.monteirosltda.core.initialcharge;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.monteirosltda.domain.model.Conta;
import com.monteirosltda.domain.model.User;
import com.monteirosltda.domain.repository.ContaRepository;
import com.monteirosltda.domain.repository.UserRepository;
import com.monteirosltda.domain.service.AuthenticationService;

@Component
public class DbService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthenticationService authenticationService;

    @Autowired
    private ContaRepository contaRepository;
	
	
	public void carregarCargaInicialFile() {
		
        DbStub.getUsers().forEach(user -> authenticationService.register(user));

        User user1 = userRepository.findByEmail("enzo.monteiro").get();
        User user2 = userRepository.findByEmail("ana.monteiro").get();

        Conta conta1 = DbStub.criateConta(user1);
        contaRepository.save(conta1);
        
        Conta conta2 = DbStub.criateConta(user2);
        contaRepository.save(conta2);
		
	}
}

