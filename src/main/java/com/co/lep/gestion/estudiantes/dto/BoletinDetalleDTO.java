package com.co.lep.gestion.estudiantes.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class BoletinDetalleDTO implements Serializable{
	
	private static final long serialVersionUID = 2913232444963665574L;
	
	private Long id;
	private BoletinDTO boletinId;
	private MateriaDTO materiaId;
	private BigDecimal nota;
	private String escalaNacional;
	private String notaFormatted;
	private Date fecCreacion;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public BoletinDTO getBoletinId() {
		return boletinId;
	}
	public void setBoletinId(BoletinDTO boletinId) {
		this.boletinId = boletinId;
	}
	public MateriaDTO getMateriaId() {
		return materiaId;
	}
	public void setMateriaId(MateriaDTO materiaId) {
		this.materiaId = materiaId;
	}
	public BigDecimal getNota() {
		return nota;
	}
	public void setNota(BigDecimal nota) {
		this.nota = nota;
	}
	public String getEscalaNacional() {
		return escalaNacional;
	}
	public void setEscalaNacional(String escalaNacional) {
		this.escalaNacional = escalaNacional;
	}
	public String getNotaFormatted() {
		return notaFormatted;
	}
	public void setNotaFormatted(String notaFormatted) {
		this.notaFormatted = notaFormatted;
	}
	public Date getFecCreacion() {
		return fecCreacion;
	}
	public void setFecCreacion(Date fecCreacion) {
		this.fecCreacion = fecCreacion;
	}
}