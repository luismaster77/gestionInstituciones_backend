package com.co.lep.gestion.estudiantes.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.co.lep.gestion.estudiantes.entity.ListaValorEntity;


public interface ConfiguracionAdicionalRepository extends JpaRepository<ListaValorEntity, Long>{

	ListaValorEntity findByCodCampo(String codCampoUrlBase);
	
}
