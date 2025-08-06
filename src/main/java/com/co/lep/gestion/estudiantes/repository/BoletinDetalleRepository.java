package com.co.lep.gestion.estudiantes.repository;

import java.math.BigDecimal;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.co.lep.gestion.estudiantes.dto.BoletinNotasPeriodosDTO;
import com.co.lep.gestion.estudiantes.entity.BoletinDetalleEntity;

public interface BoletinDetalleRepository extends JpaRepository<BoletinDetalleEntity, Long>{
	
	@Query("SELECT e FROM BoletinDetalleEntity e WHERE e.boletinId.id = :boletinId")
	public List<BoletinDetalleEntity> findByIdBoletin(@Param("boletinId") Long boletinId);
	
	@Query("SELECT new com.co.flexicraftsolutions.gestion.estudiantes.dto.BoletinNotasPeriodosDTO( " +
	        "bd.materiaId.nomMateria, " +
	        "CONCAT(MAX(COALESCE(bd.materiaId.docenteId.nomDocente, '')), ' ', MAX(COALESCE(bd.materiaId.docenteId.ape1Docente, '')), ' ', MAX(COALESCE(bd.materiaId.docenteId.ape2Docente, ''))), " +
	        "MAX(CASE WHEN pe.codPeriodoElect = 'PRI' THEN bd.nota ELSE null END), " +
	        "MAX(CASE WHEN pe.codPeriodoElect = 'SEG' THEN bd.nota ELSE null END), " +
	        "MAX(CASE WHEN pe.codPeriodoElect = 'TER' THEN bd.nota ELSE null END), " +
	        "MAX(CASE WHEN pe.codPeriodoElect = 'CUA' THEN bd.nota ELSE null END), " +
	        "MAX(bd.materiaId.intensidadHoras), " +
	        "MAX(bd.escalaNacional)) " +
	        "FROM BoletinDetalleEntity bd " +
	        "JOIN bd.boletinId b " +
	        "JOIN b.periodoElectivoId pe " +
	        "WHERE b.estudianteId.id = :estudianteId " +
	        "GROUP BY bd.materiaId.nomMateria " +
	        "ORDER BY bd.materiaId.nomMateria")
	List<BoletinNotasPeriodosDTO> obtenerNotasPorPeriodo(@Param("estudianteId") Long estudianteId);

	@Query(value = "SELECT ROUND(AVG(bd.nota), 1) FROM boletin_detalle bd " +
            "JOIN boletin b ON bd.boletin_id = b.id " +
            "WHERE b.periodo_electivo_id = :periodoElectivoId " +
            "AND b.estudiante_id = :estudianteId", nativeQuery = true)
	BigDecimal promedioTotalNotasEstudiantePorPeriodo(@Param("estudianteId") Long estudianteId, 
		                                              @Param("periodoElectivoId") Long periodoElectivoId);
}
