package com.co.flexicraftsolutions.gestion.estudiantes.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.co.flexicraftsolutions.gestion.estudiantes.constantes.Constantes;
import com.co.flexicraftsolutions.gestion.estudiantes.dto.DocenteDTO;
import com.co.flexicraftsolutions.gestion.estudiantes.entity.DocenteEntity;
import com.co.flexicraftsolutions.gestion.estudiantes.repository.impl.AdminPrincipal;

public interface DocenteRepository extends JpaRepository<DocenteEntity, Long> {
	
	public AdminPrincipal docentePrincipal = new AdminPrincipal(Constantes.INSTA_DB_PPAL);
	
	DocenteEntity save(DocenteDTO docenteEntity);

	default List<DocenteEntity> consultarDocente(DocenteEntity docenteEntity){
		return docentePrincipal.consultarDocente(docenteEntity);
	}
}
