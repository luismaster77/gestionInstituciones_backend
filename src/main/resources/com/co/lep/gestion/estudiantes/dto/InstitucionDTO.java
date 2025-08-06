package com.co.lep.gestion.estudiantes.dto;

import java.io.Serializable;

public class InstitucionDTO implements Serializable{

	private static final long serialVersionUID = 7021953671857470038L;
	
	private Long id;
	private String nomInstitucion;
	private String codInstitucion;
	private Long nit;
	private Long dane;
	private String direccion;
	private String email;
	private String descripcion;
	private String logo;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNomInstitucion() {
		return nomInstitucion;
	}
	public void setNomInstitucion(String nomInstitucion) {
		this.nomInstitucion = nomInstitucion;
	}
	public String getCodInstitucion() {
		return codInstitucion;
	}
	public void setCodInstitucion(String codInstitucion) {
		this.codInstitucion = codInstitucion;
	}
	public Long getNit() {
		return nit;
	}
	public void setNit(Long nit) {
		this.nit = nit;
	}
	public Long getDane() {
		return dane;
	}
	public void setDane(Long dane) {
		this.dane = dane;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public String getLogo() {
		return logo;
	}
	public void setLogo(String logo) {
		this.logo = logo;
	}
}
