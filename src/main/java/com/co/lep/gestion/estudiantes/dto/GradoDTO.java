package com.co.lep.gestion.estudiantes.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class GradoDTO implements Serializable{

	private static final long serialVersionUID = 93122666605330243L;
	
	private Long id;
	private String nomGrado;
	private String anioElectivo;
	private InstitucionDTO institucionId;
	private List<MateriaDTO> materiasList;
	private DocenteDTO docenteId;
	private Date fecCreacion;
	
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
	public String getAnioElectivo() {
		return anioElectivo;
	}
	public void setAnioElectivo(String anioElectivo) {
		this.anioElectivo = anioElectivo;
	}
	
	public InstitucionDTO getInstitucionId() {
		return institucionId;
	}
	public void setInstitucionId(InstitucionDTO institucionId) {
		this.institucionId = institucionId;
	}
	public List<MateriaDTO> getMateriasList() {
		return materiasList;
	}
	public void setMateriasList(List<MateriaDTO> materiasList) {
		this.materiasList = materiasList;
	}
	
	public DocenteDTO getDocenteId() {
		return docenteId;
	}
	public void setDocenteId(DocenteDTO docenteId) {
		this.docenteId = docenteId;
	}
	public Date getFecCreacion() {
		return fecCreacion;
	}
	public void setFecCreacion(Date fecCreacion) {
		this.fecCreacion = fecCreacion;
	}
}