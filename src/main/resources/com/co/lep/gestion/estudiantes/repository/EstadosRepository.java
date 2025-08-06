package com.co.flexicraftsolutions.gestion.estudiantes.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.co.flexicraftsolutions.gestion.estudiantes.entity.EstadoEntity;

public interface EstadosRepository extends JpaRepository<EstadoEntity, Long> {

	List<EstadoEntity> findByCodCampo(String codCampo);

	@Query("SELECT e FROM EstadoEntity e " +
	           "WHERE (:codCampo IS NULL OR e.codCampo = :codCampo) " +
	           "AND (:codValor IS NULL OR e.codValor = :codValor)")
	EstadoEntity consultarEstadosByCodValor(@Param("codCampo") String codCampo,@Param("codValor") String codValor);

	@Query("SELECT e.id FROM EstadoEntity e WHERE e.codValor = :codigo AND e.codCampo = 'CD_ESTADO_ESTUDIANTE' ")
	Long findByCodEstado(@Param("codigo") String codigo);
	
}
