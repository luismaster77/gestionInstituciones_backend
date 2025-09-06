package com.co.lep.gestion.estudiantes.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.co.lep.gestion.estudiantes.entity.EstudianteNotasEntity;

@Repository
public interface EstudiantesNotaRepository extends JpaRepository<EstudianteNotasEntity, Long>{
	
	@Query(value = """
		    SELECT en.*
		    FROM estudiante_nota en
		    JOIN detalle_evaluacion_estudiante dee 
		        ON en.id = dee.estudiante_nota_id
		    WHERE en.usuario_id = :usuarioId
		      AND (COALESCE(:estudianteId, dee.estudiante_id) = dee.estudiante_id)
		      AND (COALESCE(:materiaId, en.materia_id) = en.materia_id)
		      AND (COALESCE(:gradoId, en.grado_id) = en.grado_id)
		      AND (COALESCE(:periodoElectivoId, en.periodo_electivo_id) = en.periodo_electivo_id)
		      AND (
		            :fecha IS NULL 
		            OR (
		                en.fec_evaluacion >= STR_TO_DATE(CONCAT(:fecha, ' 00:00:00'), '%d-%m-%Y %H:%i:%s')
		                AND en.fec_evaluacion <= STR_TO_DATE(CONCAT(:fecha, ' 23:59:59'), '%d-%m-%Y %H:%i:%s')
		            )
		          )
		      GROUP BY en.id
		    """, nativeQuery = true)
		List<EstudianteNotasEntity> buscarNotasEstudiante(
		        @Param("usuarioId") Long usuarioId,
		        @Param("estudianteId") Long estudianteId,
		        @Param("materiaId") Long materiaId,
		        @Param("gradoId") Long gradoId,
		        @Param("periodoElectivoId") Long periodoElectivoId,
		        @Param("fecha") String fecha
		);

	
}
