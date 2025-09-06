package com.co.lep.gestion.estudiantes;

import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.web.client.RestTemplate;

import com.co.lep.gestion.estudiantes.mapper.MapperConfig;
import com.co.lep.gestion.estudiantes.service.impl.BoletinConfiguracionService;
import com.ulisesbocchio.jasyptspringboot.annotation.EnableEncryptableProperties;
import jakarta.annotation.PostConstruct;
import com.co.lep.gestion.estudiantes.tunnel.SshTunnelConfig;

@SpringBootApplication
@EnableEncryptableProperties
@Import(MapperConfig.class)
public class Application {

	@Autowired
	private DataSource dataSource;
	
	@Autowired
    BoletinConfiguracionService configuracionService;
	
	public static void main(String[] args) {
		new SpringApplicationBuilder(Application.class)
			.initializers(new SshTunnelConfig())
			.run(args);
	}
	
	@Bean 
	RestTemplate restTemplate() {
		return new RestTemplate();
	}
	
	@PostConstruct
    public void init() {
        if (dataSource != null) {
            System.out.println("Conectado a la base de datos exitosamente");
        } else {
            System.out.println("Error al conectar a la base de datos");
        }
        
        if (configuracionService != null) {
        	configuracionService.sincronizarConfiguracion();
        	System.out.println("Configuración de boletines sincronizada correctamente.");
        } else {
        	System.out.println("Error al sincronizar configuración de boletines.");
        }
    }
}
