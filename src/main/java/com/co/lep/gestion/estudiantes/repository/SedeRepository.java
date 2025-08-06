package com.co.lep.gestion.estudiantes.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.co.lep.gestion.estudiantes.dto.SedeDTO;
import com.co.lep.gestion.estudiantes.entity.SedeEntity;

public interface SedeRepository extends JpaRepository<SedeEntity, Long> {
	
	SedeEntity save(SedeDTO sedeDTO);
}
