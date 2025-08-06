package com.co.lep.gestion.estudiantes;

import static org.junit.jupiter.api.Assertions.fail;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import com.co.lep.gestion.estudiantes.dto.BoletinDTO;
import com.co.lep.gestion.estudiantes.entity.BoletinDetalleEntity;
import com.co.lep.gestion.estudiantes.impl.service.BoletinService;
import com.co.lep.gestion.estudiantes.repository.BoletinDetalleRepository;
import com.co.lep.gestion.estudiantes.repository.BoletinRepository;
import com.co.lep.gestion.estudiantes.repository.impl.AdminPrincipal;

@SpringBootTest
class GestionTest {
	
	@InjectMocks
    private BoletinService boletinService;
	
	@InjectMocks
    private AdminPrincipal adminPrincipal;
	
	@Mock
    private BoletinDetalleRepository boletinDetalleRepository;
	
	@Mock
	private BoletinRepository boletinRepository;
    
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }
    

	//@Test
	void test() {
		
		BoletinDTO boletinDTO = new BoletinDTO();
		boletinDTO.setId(5L);
		
		List<BoletinDetalleEntity> detalleEntities = boletinDetalleRepository.findByIdBoletin(5L);
		
		for (BoletinDetalleEntity boletinDetalleEntity : detalleEntities) {
			System.out.print(boletinDetalleEntity.getId());			
		}
		
		fail("Not yet implemented");
	}
	
	@Test
	void numHorasNoAsistidas1() {
		Integer numHorasNoAsistidas = adminPrincipal.consultarNumHorasNoAsistidasEstudiante(7L,8L,4L);
		System.out.println("Numero de horas: "+numHorasNoAsistidas);
	}

}
