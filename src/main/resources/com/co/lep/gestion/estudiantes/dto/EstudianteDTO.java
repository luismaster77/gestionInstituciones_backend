package com.co.lep.gestion.estudiantes.dto;

import java.io.Serializable;
import java.util.Date;

public class EstudianteDTO implements Serializable{
	
	private static final long serialVersionUID = -3758269476214196856L;
	
	private Long id;
	private String codEstudiante;
    private TipoDocumentoDTO tipoDocumId;
    private String codDocum;
    private String nomEstudiante;
    private String ape1Estudiante;
    private String ape2Estudiante;
    private String codDocente;
    private String celular;
    private String email;
    private GradoDTO gradoId;
    private EstadoDTO estadoId;
    private Date fecRegistro;
    
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getCodEstudiante() {
		return codEstudiante;
	}
	public void setCodEstudiante(String codEstudiante) {
		this.codEstudiante = codEstudiante;
	}
	
	public TipoDocumentoDTO getTipoDocumId() {
		return tipoDocumId;
	}
	public void setTipoDocumId(TipoDocumentoDTO tipoDocumId) {
		this.tipoDocumId = tipoDocumId;
	}
	public String getCodDocum() {
		return codDocum;
	}
	public void setCodDocum(String codDocum) {
		this.codDocum = codDocum;
	}
	public String getNomEstudiante() {
		return nomEstudiante;
	}
	public void setNomEstudiante(String nomEstudiante) {
		this.nomEstudiante = nomEstudiante;
	}
	public String getApe1Estudiante() {
		return ape1Estudiante;
	}
	public void setApe1Estudiante(String ape1Estudiante) {
		this.ape1Estudiante = ape1Estudiante;
	}
	public String getApe2Estudiante() {
		return ape2Estudiante;
	}
	public void setApe2Estudiante(String ape2Estudiante) {
		this.ape2Estudiante = ape2Estudiante;
	}
	public String getCodDocente() {
		return codDocente;
	}
	public void setCodDocente(String codDocente) {
		this.codDocente = codDocente;
	}
	public String getCelular() {
		return celular;
	}
	public void setCelular(String celular) {
		this.celular = celular;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public GradoDTO getGradoId() {
		return gradoId;
	}
	public void setGradoId(GradoDTO gradoId) {
		this.gradoId = gradoId;
	}
	public EstadoDTO getEstadoId() {
		return estadoId;
	}
	public void setEstadoId(EstadoDTO estadoId) {
		this.estadoId = estadoId;
	}
	public Date getFecRegistro() {
		return fecRegistro;
	}
	public void setFecRegistro(Date fecRegistro) {
		this.fecRegistro = fecRegistro;
	}
}
