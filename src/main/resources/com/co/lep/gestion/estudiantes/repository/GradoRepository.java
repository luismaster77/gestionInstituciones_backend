package com.co.lep.gestion.estudiantes.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.co.lep.gestion.estudiantes.dto.GradoDTO;
import com.co.lep.gestion.estudiantes.entity.GradoEntity;

public interface GradoRepository extends JpaRepository<GradoEntity, Long> {
	
	GradoEntity save(GradoDTO gradoDTO);

	@Query("SELECT d.codGrado FROM GradoEntity d")
	List<String> findAllCodGrados();
	
	@Query("SELECT e FROM GradoEntity e " +
	           "WHERE (:nomGrado IS NULL OR e.nomGrado = :nomGrado) " +
	           "AND (:anioElectivo IS NULL OR e.anioElectivo = :anioElectivo)" +
	           "AND (:id IS NULL OR e.id = :id)" +
	           "ORDER BY e.id")
	List<GradoEntity> findByGrados(
			@Param("nomGrado") String nomGrado, 
			@Param("anioElectivo") String anioElectivo,
			@Param("id") Long id);

	@Query("SELECT d.id FROM GradoEntity d WHERE d.codGrado = :codigo")
	Long findByCodGrado(@Param("codigo") String codigo);
}
