package com.co.lep.gestion.estudiantes.dto;

import java.io.Serializable;

public class ListaValorDTO implements Serializable{

	private static final long serialVersionUID = 3365071739099285542L;
	
	private Long id;
	private String codCampo;
	private String codItem;
	private String nomItem;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getCodCampo() {
		return codCampo;
	}
	public void setCodCampo(String codCampo) {
		this.codCampo = codCampo;
	}
	public String getCodItem() {
		return codItem;
	}
	public void setCodItem(String codItem) {
		this.codItem = codItem;
	}
	public String getNomItem() {
		return nomItem;
	}
	public void setNomItem(String nomItem) {
		this.nomItem = nomItem;
	}
}