package com.co.lep.gestion.estudiantes.dto;

import java.io.Serializable;

public class NivelDTO implements Serializable{
	
	private static final long serialVersionUID = -7181832761741206383L;
	
	private Long id;
	private String codNivel;
	private String nomNivel;
	private InstitucionDTO institucionId;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getCodNivel() {
		return codNivel;
	}
	public void setCodNivel(String codNivel) {
		this.codNivel = codNivel;
	}
	public String getNomNivel() {
		return nomNivel;
	}
	public void setNomNivel(String nomNivel) {
		this.nomNivel = nomNivel;
	}
	public InstitucionDTO getInstitucionId() {
		return institucionId;
	}
	public void setInstitucionId(InstitucionDTO institucionId) {
		this.institucionId = institucionId;
	}
}