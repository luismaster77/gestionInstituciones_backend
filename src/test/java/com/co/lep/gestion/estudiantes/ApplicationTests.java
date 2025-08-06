package com.co.lep.gestion.estudiantes;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.co.lep.gestion.estudiantes.userApp.DataLoader;

@SpringBootTest
class ApplicationTests {
	
	@Autowired
	DataLoader dataLoader;
	
	@Test
	void contextLoads() {
		
		//dataLoader.loadTestData();
		//dataLoader.encriptarPassword(null);
	}
	
}
