package com.co.lep.gestion.estudiantes.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.co.lep.gestion.estudiantes.entity.BoletinEntity;
import com.co.lep.gestion.estudiantes.entity.PeriodoElectivoEntity;

public interface BoletinRepository extends JpaRepository<BoletinEntity, Long>{

	@Query("SELECT b.periodoElectivoId " +
	           "FROM BoletinEntity b " +
	           "WHERE b.estudianteId.id = :estudianteId")
	List<PeriodoElectivoEntity> obtenerPeriodosPorEstudiante(@Param("estudianteId") Long estudianteId);

	Optional<BoletinEntity> findByEstudianteIdId(Long estudianteId);

	Optional<BoletinEntity> findByEstudianteIdIdAndPeriodoElectivoIdId(Long estudianteId, Long idPeriodo);

}
