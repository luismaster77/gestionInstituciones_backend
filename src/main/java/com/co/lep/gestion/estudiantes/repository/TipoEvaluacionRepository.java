package com.co.lep.gestion.estudiantes.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.co.lep.gestion.estudiantes.entity.TipoEvaluacionEntity;

@Repository
public interface TipoEvaluacionRepository extends JpaRepository<TipoEvaluacionEntity, Long> {

	@Query("SELECT d FROM TipoEvaluacionEntity d WHERE (:institucionId IS NULL OR d.institucionId.id = :institucionId)")
	List<TipoEvaluacionEntity> consultarTipoEvaluacion(@Param("institucionId")Long institucionId);
}
