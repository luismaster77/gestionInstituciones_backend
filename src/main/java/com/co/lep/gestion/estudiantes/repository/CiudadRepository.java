package com.co.lep.gestion.estudiantes.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.co.lep.gestion.estudiantes.entity.CiudadEntity;


public interface CiudadRepository extends JpaRepository<CiudadEntity, Long>{

	List<CiudadEntity> findByMunicipioContainingIgnoreCase(String nombre);
}
