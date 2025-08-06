package com.co.lep.gestion.estudiantes.impl.service;

import java.util.List;
import javax.validation.Valid;
import org.springframework.stereotype.Service;

import com.co.lep.gestion.estudiantes.entity.MateriaEntity;
import com.co.lep.gestion.estudiantes.repository.impl.AdminPrincipal;
@Service
public class MateriaService {

	private final AdminPrincipal adminPrincipal;
	
	public MateriaService(AdminPrincipal adminPrincipal) {
		this.adminPrincipal = adminPrincipal;
	}

	public List<MateriaEntity> consultarMaterias(@Valid MateriaEntity materiaEntity) {
		return adminPrincipal.consultarMaterias(materiaEntity);
	}
}