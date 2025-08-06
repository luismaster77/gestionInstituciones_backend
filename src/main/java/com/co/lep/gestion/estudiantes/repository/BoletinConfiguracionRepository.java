package com.co.lep.gestion.estudiantes.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.co.lep.gestion.estudiantes.entity.BoletinConfiguracionEntity;

public interface BoletinConfiguracionRepository extends JpaRepository<BoletinConfiguracionEntity, Long>{

	void deleteByNombreCampo(String columnaConfig);

	List<BoletinConfiguracionEntity> findByInstitucionIdId(Long institucionId);

	void deleteByInstitucionIdId(Long institucionId);

}
