package com.co.lep.gestion.estudiantes.dto;

import java.io.Serializable;

public class EstadoDTO implements Serializable{
	
	private static final long serialVersionUID = -3671248350248125342L;

    private Long id;
	private String codCampo;
	private String codValor;
	private String nomValor;

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

	public String getCodValor() {
		return codValor;
	}

	public void setCodValor(String codValor) {
		this.codValor = codValor;
	}

	public String getNomValor() {
		return nomValor;
	}

	public void setNomValor(String nomValor) {
		this.nomValor = nomValor;
	}

}
