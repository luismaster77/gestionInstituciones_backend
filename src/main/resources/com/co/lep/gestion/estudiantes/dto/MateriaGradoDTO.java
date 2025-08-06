package com.co.flexicraftsolutions.gestion.estudiantes.dto;

import java.io.Serializable;

public class MateriaGradoDTO implements Serializable{
	
	private static final long serialVersionUID = 3767018119608316792L;
	
	private Long id;
	private Long idMateria;
	private Long idGrado;
	private Long idDocente;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getIdMateria() {
		return idMateria;
	}
	public void setIdMateria(Long idMateria) {
		this.idMateria = idMateria;
	}
	public Long getIdGrado() {
		return idGrado;
	}
	public void setIdGrado(Long idGrado) {
		this.idGrado = idGrado;
	}
	public Long getIdDocente() {
		return idDocente;
	}
	public void setIdDocente(Long idDocente) {
		this.idDocente = idDocente;
	}
}
