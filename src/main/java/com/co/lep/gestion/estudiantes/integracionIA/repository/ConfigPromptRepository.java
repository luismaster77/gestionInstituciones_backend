package com.co.lep.gestion.estudiantes.integracionIA.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.co.lep.gestion.estudiantes.integracionIA.entity.ConfigPromptEntity;

public interface ConfigPromptRepository extends JpaRepository<ConfigPromptEntity, Long>{

	@Query("SELECT e FROM ConfigPromptEntity e WHERE (:idUsuario IS NULL OR e.usuarioId.id = :idUsuario)")
	ConfigPromptEntity findByIdUsuario(@Param("idUsuario") Long idUsuario);

}
