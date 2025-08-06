package com.co.lep.gestion.estudiantes.dto;

import java.io.Serializable;

public class SedeDTO implements Serializable{
	
	private static final long serialVersionUID = 8189491280221688280L;
	
	private Long id;
	private String nomSede;
	private String descSede;
	private String dane;
	private String direccion;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNomSede() {
		return nomSede;
	}
	public void setNomSede(String nomSede) {
		this.nomSede = nomSede;
	}
	public String getDescSede() {
		return descSede;
	}
	public void setDescSede(String descSede) {
		this.descSede = descSede;
	}
	public String getDane() {
		return dane;
	}
	public void setDane(String dane) {
		this.dane = dane;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
}
