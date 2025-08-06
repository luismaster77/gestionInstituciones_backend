package com.co.lep.gestion.estudiantes.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.co.lep.gestion.estudiantes.dto.GradoDTO;
import com.co.lep.gestion.estudiantes.entity.GradoEntity;

public interface GradoRepository extends JpaRepository<GradoEntity, Long> {
	
	GradoEntity save(GradoDTO gradoDTO);
}
