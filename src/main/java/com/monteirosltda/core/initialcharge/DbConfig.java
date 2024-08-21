package com.monteirosltda.core.initialcharge;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DbConfig {

	@Autowired
	private DbService dbService;
	
	@Value("${spring.jpa.hibernate.ddl-auto}")
	private String ddlAuto;

	@Bean
	void instanceDb() {
		if(Objects.equals(ddlAuto, "create-drop")) {

			System.out.println("Criando...");
			dbService.carregarCargaInicialFile();
		}
	}
}
