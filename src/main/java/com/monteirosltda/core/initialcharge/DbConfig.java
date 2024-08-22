package com.monteirosltda.core.initialcharge;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
public class DbConfig {

	private final DbService dbService;

	public DbConfig(DbService dbService) {
		this.dbService = dbService;
	}
	
	@Value("${spring.jpa.hibernate.ddl-auto}")
	private String ddlAuto;

	@Bean
	void instanceDb() {
		if(Objects.equals(ddlAuto, "create-drop")) {

			log.info("Iniciando carga...");
			dbService.carregarCargaInicialFile();
			log.info("Carga finalizada...");
		}
	}
}
