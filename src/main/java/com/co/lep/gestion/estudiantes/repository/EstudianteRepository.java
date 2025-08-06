package com.co.lep.gestion.estudiantes.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.co.lep.gestion.estudiantes.dto.EstudianteDTO;
import com.co.lep.gestion.estudiantes.entity.EstudianteEntity;

@Repository
public interface EstudianteRepository extends JpaRepository<EstudianteEntity, Long>{
	
	EstudianteEntity save(EstudianteDTO estudianteDTO);
}
