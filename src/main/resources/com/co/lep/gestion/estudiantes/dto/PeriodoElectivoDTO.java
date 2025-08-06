package com.co.lep.gestion.estudiantes.dto;

import java.io.Serializable;

public class PeriodoElectivoDTO implements Serializable{

	private static final long serialVersionUID = -4231400412307846013L;
	
	private Long id;
	private String codPeriodoElect;
	private String nomPeriodoElect;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getCodPeriodoElect() {
		return codPeriodoElect;
	}
	public void setCodPeriodoElect(String codPeriodoElect) {
		this.codPeriodoElect = codPeriodoElect;
	}
	public String getNomPeriodoElect() {
		return nomPeriodoElect;
	}
	public void setNomPeriodoElect(String nomPeriodoElect) {
		this.nomPeriodoElect = nomPeriodoElect;
	}
	
	
}
