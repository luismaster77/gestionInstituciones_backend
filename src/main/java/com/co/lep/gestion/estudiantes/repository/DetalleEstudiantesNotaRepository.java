package com.co.lep.gestion.estudiantes.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.co.lep.gestion.estudiantes.entity.DetalleEstudianteNotasEntity;

public interface DetalleEstudiantesNotaRepository extends JpaRepository<DetalleEstudianteNotasEntity, Long>{

	@Query("SELECT e FROM DetalleEstudianteNotasEntity e WHERE e.estudianteNotasId.id = :idDetalle AND (:idEstudiante IS NULL OR e.estudianteId.id = :idEstudiante)")
	List<DetalleEstudianteNotasEntity> findByEstudianteNotasIdId(@Param("idDetalle") Long idDetalle, @Param("idEstudiante") Long idEstudiante);
}
