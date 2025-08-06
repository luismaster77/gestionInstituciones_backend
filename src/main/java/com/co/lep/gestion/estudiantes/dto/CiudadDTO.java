package com.co.lep.gestion.estudiantes.dto;

import java.io.Serializable;

public class CiudadDTO implements Serializable{
	
	private static final long serialVersionUID = -6324777095787240049L;
	
	private Long id;
	private String codPais;
	private String municipio;
	private String dpto;
	private String pais;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getCodPais() {
		return codPais;
	}
	public void setCodPais(String codPais) {
		this.codPais = codPais;
	}
	public String getMunicipio() {
		return municipio;
	}
	public void setMunicipio(String municipio) {
		this.municipio = municipio;
	}
	public String getDpto() {
		return dpto;
	}
	public void setDpto(String dpto) {
		this.dpto = dpto;
	}
	public String getPais() {
		return pais;
	}
	public void setPais(String pais) {
		this.pais = pais;
	}
}