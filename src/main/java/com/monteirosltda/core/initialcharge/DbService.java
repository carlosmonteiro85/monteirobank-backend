package com.monteirosltda.core.initialcharge;

import java.util.Optional;

import org.springframework.stereotype.Component;

import com.monteirosltda.domain.model.User;
import com.monteirosltda.domain.repository.UserRepository;
import com.monteirosltda.domain.service.AuthenticationService;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class DbService {

    private final UserRepository userRepository;
    private final AuthenticationService authenticationService;
	
	public void carregarCargaInicialFile() {
		
        DbStub.getUsers().forEach(authenticationService::register);

        Optional<User> user1 = userRepository.findByEmail("carlos.monteiro");
        if(user1.isPresent()){
            userRepository.save(user1.get());
        }
        
        Optional<User> user2 = userRepository.findByEmail("dayane.monteiro");
        if(user2.isPresent()){
            userRepository.save(user2.get());
        }
	}
}

