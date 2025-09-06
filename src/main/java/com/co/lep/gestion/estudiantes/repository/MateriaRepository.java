package com.co.lep.gestion.estudiantes.repository;

import java.util.List;

import javax.validation.Valid;

import org.springframework.data.jpa.repository.JpaRepository;

import com.co.lep.gestion.estudiantes.dto.MateriaDTO;
import com.co.lep.gestion.estudiantes.entity.MateriaEntity;

public interface MateriaRepository extends JpaRepository<MateriaEntity, Long> {

	MateriaEntity save(MateriaDTO materiaDTO);

	List<MateriaEntity> findByDocenteIdId(@Valid Long docenteId);
}
