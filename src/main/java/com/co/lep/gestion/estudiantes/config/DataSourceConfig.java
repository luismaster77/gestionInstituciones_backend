package com.co.lep.gestion.estudiantes.config;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import com.co.lep.gestion.estudiantes.constantes.Constantes;
import com.devsenior.vault.util.VaultUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

@Configuration
public class DataSourceConfig {
	
	@Bean
    DataSource dataSource() {
		VaultUtil vaultUtil = new VaultUtil(Constantes.VAULT_URL, 
				Constantes.ROLE_ID, 
				Constantes.SECRET_ID, 
				Constantes.SECRET_PATH,
				new RestTemplate(),
				new ObjectMapper());

        String password = vaultUtil.getSecretValue(Constantes.ENCRYPTOR_KEY_DB);

        HikariConfig config = new HikariConfig();
        config.setJdbcUrl("jdbc:mysql://localhost:3307/devseumq_estudiantes");
        config.setUsername("devseumq_estudiantes");
        config.setPassword(password);
        config.setDriverClassName("com.mysql.cj.jdbc.Driver");

        return new HikariDataSource(config);
    }
}
