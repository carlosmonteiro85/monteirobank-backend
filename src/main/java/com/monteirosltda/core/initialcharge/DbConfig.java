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

	@Value("${spring.jpa.hibernate.ddl-auto}")
	private String ddlAuto;

	public DbConfig(DbService dbService) {
		this.dbService = dbService;
	}

	@Bean
	void instanceDb() {
		if (Objects.equals(ddlAuto, "update")) {

			log.info("Inicializando carga ...");
			dbService.carregarCargaInicialFile();
			log.info("Inicializando carga ...");
		}
	}
}
