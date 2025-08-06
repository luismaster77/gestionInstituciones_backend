package com.co.flexicraftsolutions.gestion.estudiantes;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.web.client.RestTemplate;

import com.co.flexicraftsolutions.gestion.estudiantes.mapper.MapperConfig;

import jakarta.annotation.PostConstruct;

@SpringBootApplication
@Import(MapperConfig.class)
public class Application {

	@Autowired
	private DataSource dataSource;
	  
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
	
	@Bean 
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}
	
	@PostConstruct
    public void init() {
        if (dataSource != null) {
            System.out.println("Connected to database: " + dataSource.toString());
        } else {
            System.out.println("Failed to connect to database");
        }
    }
}
