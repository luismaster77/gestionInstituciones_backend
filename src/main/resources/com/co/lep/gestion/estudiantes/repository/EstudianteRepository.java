package com.co.lep.gestion.estudiantes.repository;

import java.util.List;

import javax.validation.Valid;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.co.lep.gestion.estudiantes.constantes.Constantes;
import com.co.lep.gestion.estudiantes.dto.EstudianteDTO;
import com.co.lep.gestion.estudiantes.entity.EstudianteEntity;
import com.co.lep.gestion.estudiantes.repository.impl.AdminPrincipal;

@Repository
public interface EstudianteRepository extends JpaRepository<EstudianteEntity, Long>{

	public AdminPrincipal estudiantePrincipal = new AdminPrincipal(Constantes.INSTA_DB_PPAL);
	
	EstudianteEntity save(EstudianteDTO estudianteDTO);

	@Query("SELECT d.codEstudiante FROM EstudianteEntity d")
	List<String> findAllCodEstudiantes();
	
	default List<EstudianteEntity> consultarEstudiantes(@Valid EstudianteEntity estudianteEntity){
		
		return estudiantePrincipal.consultarEstudiantes(estudianteEntity);
	}
}
