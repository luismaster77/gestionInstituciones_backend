package com.co.lep.gestion.estudiantes.service.impl;

import java.util.List;
import org.springframework.stereotype.Service;

import com.co.lep.gestion.estudiantes.entity.EstudianteEntity;
import com.co.lep.gestion.estudiantes.repository.impl.AdminPrincipal;

@Service
public class EstudianteService {
	
	private final AdminPrincipal adminPrincipal;
	
    public EstudianteService(AdminPrincipal adminPrincipal) {
        this.adminPrincipal = adminPrincipal;
    }
	
	public List<EstudianteEntity> consultarEstudiantes(EstudianteEntity estudianteEntity) {
        return adminPrincipal.consultarEstudiantes(estudianteEntity);
    }
}
