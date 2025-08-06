package com.co.lep.gestion.estudiantes.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.co.lep.gestion.estudiantes.entity.PeriodoElectivoEntity;

public interface PeriodoElectivoRepository extends JpaRepository<PeriodoElectivoEntity, Long> {

	@Query("SELECT e.codPeriodoElect FROM PeriodoElectivoEntity e")
	List<String> findCodPeriodoElectivos();

	@Query("SELECT d FROM PeriodoElectivoEntity d "
			+ "WHERE (:nomPeriodoElect IS NULL OR d.nomPeriodoElect LIKE CONCAT('%', :nomPeriodoElect, '%')) "
			+ "AND (:codPeriodoElect IS NULL OR d.codPeriodoElect =: codPeriodoElect)")
	List<PeriodoElectivoEntity> consultarPeriodoElectivo(@Param("nomPeriodoElect") String nomPeriodoElect , @Param("codPeriodoElect") String codPeriodoElect);
}
