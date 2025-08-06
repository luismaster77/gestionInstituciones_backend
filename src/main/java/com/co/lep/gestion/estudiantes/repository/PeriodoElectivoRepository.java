package com.co.lep.gestion.estudiantes.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.co.lep.gestion.estudiantes.entity.PeriodoElectivoEntity;

public interface PeriodoElectivoRepository extends JpaRepository<PeriodoElectivoEntity, Long> {

	@Query("SELECT e.codPeriodoElect FROM PeriodoElectivoEntity e WHERE (:institucionId IS NULL OR e.institucionId.id = :institucionId)")
	List<String> findCodPeriodoElectivos(@Param("institucionId") Long institucionId);

	@Query("SELECT d FROM PeriodoElectivoEntity d "
			+ "WHERE (:nomPeriodoElect IS NULL OR d.nomPeriodoElect LIKE CONCAT('%', :nomPeriodoElect, '%')) "
			+ "AND (:institucionId IS NULL OR d.institucionId.id = :institucionId)")
	public List<PeriodoElectivoEntity> consultarPeriodoElectivo(@Param("nomPeriodoElect") String nomPeriodoElect , @Param("institucionId") Long institucionId);

	@Query("SELECT d FROM PeriodoElectivoEntity d "
			+ "WHERE (:institucionId IS NULL OR d.institucionId.id = :institucionId)")
	List<PeriodoElectivoEntity> consularPeriodoElectivo(@Param("institucionId") Long institucionId);
}
