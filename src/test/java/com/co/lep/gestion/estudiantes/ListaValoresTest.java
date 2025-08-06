package com.co.lep.gestion.estudiantes;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import com.co.lep.gestion.estudiantes.impl.service.ListaValoresService;
import com.co.lep.gestion.estudiantes.repository.EstadosRepository;

@SpringBootTest
class ListaValoresTest {

	@InjectMocks
    private ListaValoresService listaValoresService;

    @Mock
    private EstadosRepository estadosRepository;
    
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }
    
    @Test
    void consultarEstadosApp() { }

}
