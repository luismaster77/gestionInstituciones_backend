package com.co.lep.gestion.estudiantes.service.impl;

import java.util.List;
import org.springframework.stereotype.Service;

import com.co.lep.gestion.estudiantes.entity.DocenteEntity;
import com.co.lep.gestion.estudiantes.repository.impl.AdminPrincipal;


@Service
public class DocenteService {

	private final AdminPrincipal adminPrincipal;

	public DocenteService(AdminPrincipal adminPrincipal) {
		this.adminPrincipal = adminPrincipal;
	}
	
	public List<DocenteEntity> consultarDocente(DocenteEntity docenteEntity){
		return adminPrincipal.consultarDocente(docenteEntity);
	}
}
