package com.co.lep.gestion.estudiantes.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.co.lep.gestion.estudiantes.entity.InstitucionEntity;

public interface InstitucionRepository extends JpaRepository<InstitucionEntity, Long>{

	@Query("SELECT d FROM InstitucionEntity d "
			+ "WHERE (:nomInstitucion IS NULL OR d.nomInstitucion LIKE CONCAT('%', :nomInstitucion, '%')) "
		    + "AND (:nit IS NULL OR d.nit =: nit)"
		    + "AND (d.id = :institucionId OR :institucionId IS NULL)")
	List<InstitucionEntity> consultarInstitucion(
			@Param("nomInstitucion") String nomInstitucion, @Param("nit") Long nit, @Param("institucionId") Long usuarioId);
}
