package com.co.lep.gestion.estudiantes.service.impl;

import java.util.List;
import org.springframework.stereotype.Service;

import com.co.lep.gestion.estudiantes.entity.GradoEntity;
import com.co.lep.gestion.estudiantes.repository.impl.AdminPrincipal;

@Service
public class GradosService {
	
	private final AdminPrincipal adminPrincipal;

	public GradosService(AdminPrincipal adminPrincipal) {
		this.adminPrincipal = adminPrincipal;
	}
	
	public List<GradoEntity> consultarGrado(GradoEntity gradoEntity){
		return adminPrincipal.consultarGrados(gradoEntity);
	}
}