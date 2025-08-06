package com.co.lep.gestion.estudiantes.dto;

import java.io.Serializable;

public class TipoDocumentoDTO implements Serializable{
	
	private static final long serialVersionUID = -1854973592082406666L;
	
	private Long id;
	private String codTipoDocum;
	private String nomTipoDocum;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getCodTipoDocum() {
		return codTipoDocum;
	}
	public void setCodTipoDocum(String codTipoDocum) {
		this.codTipoDocum = codTipoDocum;
	}
	public String getNomTipoDocum() {
		return nomTipoDocum;
	}
	public void setNomTipoDocum(String nomTipoDocum) {
		this.nomTipoDocum = nomTipoDocum;
	}

}
