package com.co.lep.gestion.estudiantes.dto;

import java.io.Serializable;
import java.util.List;

public class GradoDTO implements Serializable{

	private static final long serialVersionUID = 93122666605330243L;
	
	private Long id;
	private String nomGrado;
	private String codGrado;
	private String anioElectivo;
	private List<MateriaDTO> materiasList;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNomGrado() {
		return nomGrado;
	}
	public void setNomGrado(String nomGrado) {
		this.nomGrado = nomGrado;
	}
	public String getCodGrado() {
		return codGrado;
	}
	public void setCodGrado(String codGrado) {
		this.codGrado = codGrado;
	}
	
	public String getAnioElectivo() {
		return anioElectivo;
	}
	public void setAnioElectivo(String anioElectivo) {
		this.anioElectivo = anioElectivo;
	}
	public List<MateriaDTO> getMateriasList() {
		return materiasList;
	}
	public void setMateriasList(List<MateriaDTO> materiasList) {
		this.materiasList = materiasList;
	}
}
