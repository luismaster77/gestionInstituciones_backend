package com.co.lep.gestion.estudiantes.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.co.lep.gestion.estudiantes.entity.GradoMateriasEntity;
import com.co.lep.gestion.estudiantes.entity.MateriaEntity;

public interface GradoMateriasRepository extends JpaRepository<GradoMateriasEntity,Long>{
	
	@Query("SELECT gm.materiaId FROM GradoMateriasEntity gm WHERE gm.gradoId.id = :gradoId")
	List<MateriaEntity> findMateriasByGradoId(@Param("gradoId") Long gradoId);

	List<GradoMateriasEntity> findByGradoIdId(Long id);

	
}
