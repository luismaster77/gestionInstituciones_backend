package com.co.lep.gestion.estudiantes.repository;

import java.util.List;

import javax.validation.Valid;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.co.lep.gestion.estudiantes.constantes.Constantes;
import com.co.lep.gestion.estudiantes.dto.MateriaDTO;
import com.co.lep.gestion.estudiantes.entity.MateriaEntity;
import com.co.lep.gestion.estudiantes.repository.impl.AdminPrincipal;

public interface MateriaRepository extends JpaRepository<MateriaEntity, Long> {
	
	public AdminPrincipal adminPrincipal = new AdminPrincipal(Constantes.INSTA_DB_PPAL);

	MateriaEntity save(MateriaDTO materiaDTO);
	
	@Query("SELECT d.codMateria FROM MateriaEntity d")
	List<String> findAllCodMateria();

	default List<MateriaEntity> consultarMaterias(@Valid MateriaEntity materiaEntity){
		return adminPrincipal.consultarMaterias(materiaEntity);
	}
}
