package com.co.lep.gestion.estudiantes.dto;

import java.io.Serializable;
import java.util.Date;

public class PeriodoElectivoDTO implements Serializable{

	private static final long serialVersionUID = -4231400412307846013L;
	
	private Long id;
	private String codPeriodoElect;
	private String nomPeriodoElect;
	private InstitucionDTO institucionId;
	private Date fecCreacion;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getCodPeriodoElect() {
		return codPeriodoElect;
	}
	public void setCodPeriodoElect(String codPeriodoElect) {
		this.codPeriodoElect = codPeriodoElect;
	}
	public String getNomPeriodoElect() {
		return nomPeriodoElect;
	}
	public void setNomPeriodoElect(String nomPeriodoElect) {
		this.nomPeriodoElect = nomPeriodoElect;
	}
	public InstitucionDTO getInstitucionId() {
		return institucionId;
	}
	public void setInstitucionId(InstitucionDTO institucionId) {
		this.institucionId = institucionId;
	}
	public Date getFecCreacion() {
		return fecCreacion;
	}
	public void setFecCreacion(Date fecCreacion) {
		this.fecCreacion = fecCreacion;
	}
}
