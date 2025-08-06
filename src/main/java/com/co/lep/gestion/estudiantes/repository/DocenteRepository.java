package com.co.lep.gestion.estudiantes.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.co.lep.gestion.estudiantes.dto.DocenteDTO;
import com.co.lep.gestion.estudiantes.entity.DocenteEntity;

public interface DocenteRepository extends JpaRepository<DocenteEntity, Long> {
	
	DocenteEntity save(DocenteDTO docenteEntity);
	
	@Query("SELECT e FROM DocenteEntity e WHERE e.usuarioId.username = :codUsuario")
	DocenteEntity consultarDocentePorUsuario(@Param("codUsuario") String codUsuario);

//	@Query("SELECT e FROM DocenteEntity e WHERE e.usuarioId.id = :usuarioId limit 1")
//    Optional<DocenteEntity> findDocenteByUserId(@Param("usuarioId") Long usuarioId);
	Optional<DocenteEntity> findTop1ByUsuarioId_Id(Long usuarioId);

	@Query("SELECT e FROM DocenteEntity e WHERE (:institucionId IS NULL OR e.institucionId.id = :institucionId)")
	List<DocenteEntity> findByInstitucionId(@Param("institucionId") Long institucionId);
}
