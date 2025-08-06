package com.co.lep.gestion.estudiantes.impl.service;

import java.util.List;
import org.springframework.stereotype.Service;

import com.co.lep.gestion.estudiantes.entity.SedeEntity;
import com.co.lep.gestion.estudiantes.repository.impl.AdminPrincipal;

@Service
public class SedeService {
	
	private final AdminPrincipal adminPrincipal;

	public SedeService(AdminPrincipal adminPrincipal) {
		this.adminPrincipal = adminPrincipal;
	}
	
	public List<SedeEntity> consultarSede(SedeEntity sedeEntity){
		return adminPrincipal.consultarSede(sedeEntity);
	}
}