package com.co.lep.gestion.estudiantes.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.co.lep.gestion.estudiantes.dto.TipoDocumentoDTO;
import com.co.lep.gestion.estudiantes.entity.GradoEntity;
import com.co.lep.gestion.estudiantes.entity.TipoDocumentoEntity;

public interface TipoDocumentosRepository extends JpaRepository<TipoDocumentoEntity, Long> {
	
	TipoDocumentoEntity save(TipoDocumentoDTO tipoDocumentoDTO);

	@Query("SELECT e FROM TipoDocumentoEntity e " +
	           "WHERE (:nomTipoDocum IS NULL OR e.nomTipoDocum = :nomTipoDocum) " +
	           "AND (:codTipoDocum IS NULL OR e.codTipoDocum = :codTipoDocum)" +
	           "AND (:id IS NULL OR e.id = :id)" +
	           "ORDER BY e.id")
	
	List<GradoEntity> findByTipoDocumentos(
			@Param("nomTipoDocum") String nomTipoDocum, 
			@Param("codTipoDocum") String codTipoDocum,
			@Param("id") Long id);

	@Query("SELECT e.id FROM TipoDocumentoEntity e WHERE e.codTipoDocum = :codigo")
	Long findByCodTipDocum(@Param("codigo") String codigo);
}
