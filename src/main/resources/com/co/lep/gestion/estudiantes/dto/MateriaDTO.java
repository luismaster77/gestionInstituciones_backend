package com.co.flexicraftsolutions.gestion.estudiantes.dto;

import java.io.Serializable;
import java.util.List;

public class MateriaDTO implements Serializable{

	private static final long serialVersionUID = 8704714652515075123L;
	
	private Long id;
	private String nomMateria;
	private String codMateria;
	private NivelDTO nivelId;
	private DocenteDTO docenteId;
	private List<DocenteDTO> docentesList;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNomMateria() {
		return nomMateria;
	}
	public void setNomMateria(String nomMateria) {
		this.nomMateria = nomMateria;
	}
	public String getCodMateria() {
		return codMateria;
	}
	public void setCodMateria(String codMateria) {
		this.codMateria = codMateria;
	}
	public NivelDTO getNivelId() {
		return nivelId;
	}
	public void setNivelId(NivelDTO nivelId) {
		this.nivelId = nivelId;
	}
	public DocenteDTO getDocenteId() {
		return docenteId;
	}
	public void setDocenteId(DocenteDTO docenteId) {
		this.docenteId = docenteId;
	}
	public List<DocenteDTO> getDocentesList() {
		return docentesList;
	}
	public void setDocentesList(List<DocenteDTO> docentesList) {
		this.docentesList = docentesList;
	}
}
