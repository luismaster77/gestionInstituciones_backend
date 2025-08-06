package com.co.lep.gestion.estudiantes.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.co.lep.gestion.estudiantes.entity.NivelEntity;

public interface NivelesRepository extends JpaRepository<NivelEntity, Long> {

	List<NivelEntity> findAll();
}
