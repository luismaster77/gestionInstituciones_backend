package com.co.lep.gestion.estudiantes.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class MateriaDTO implements Serializable{

	private static final long serialVersionUID = 8704714652515075123L;
	
	private Long id;
	private String nomMateria;
	private NivelDTO nivelId;
	private DocenteDTO docenteId;
	private InstitucionDTO institucionId;
	private BigDecimal nota;
	private Integer intensidadHoras;
	private Date fecCreacion;
	
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
	public InstitucionDTO getInstitucionId() {
		return institucionId;
	}
	public void setInstitucionId(InstitucionDTO institucionId) {
		this.institucionId = institucionId;
	}
	public BigDecimal getNota() {
		return nota;
	}
	public void setNota(BigDecimal nota) {
		this.nota = nota;
	}
	public Integer getIntensidadHoras() {
		return intensidadHoras;
	}
	public void setIntensidadHoras(Integer intensidadHoras) {
		this.intensidadHoras = intensidadHoras;
	}
	public Date getFecCreacion() {
		return fecCreacion;
	}
	public void setFecCreacion(Date fecCreacion) {
		this.fecCreacion = fecCreacion;
	}
}