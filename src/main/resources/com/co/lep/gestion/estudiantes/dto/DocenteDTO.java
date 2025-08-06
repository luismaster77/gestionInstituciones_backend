package com.co.lep.gestion.estudiantes.dto;

import java.io.Serializable;
import java.util.Date;

import com.co.lep.gestion.estudiantes.security.entity.User;

public class DocenteDTO implements Serializable{

	private static final long serialVersionUID = -6746591800625106732L;
	
	private Long id;
	private String codDocente;
	private String nomDocente;
	private String ape1Docente;
	private String ape2Docente;
	private TipoDocumentoDTO tipDocumId;
	private String codDocum;
	private String celular;
	private String email;
	private User usuarioId;
	private InstitucionDTO institucionId;
	private EstadoDTO estadoId;
	private Date fecRegistro;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getCodDocente() {
		return codDocente;
	}
	public void setCodDocente(String codDocente) {
		this.codDocente = codDocente;
	}
	public String getNomDocente() {
		return nomDocente;
	}
	public void setNomDocente(String nomDocente) {
		this.nomDocente = nomDocente;
	}
	public String getApe1Docente() {
		return ape1Docente;
	}
	public void setApe1Docente(String ape1Docente) {
		this.ape1Docente = ape1Docente;
	}
	public String getApe2Docente() {
		return ape2Docente;
	}
	public void setApe2Docente(String ape2Docente) {
		this.ape2Docente = ape2Docente;
	}
	public TipoDocumentoDTO getTipDocumId() {
		return tipDocumId;
	}
	public void setTipDocumId(TipoDocumentoDTO tipDocumId) {
		this.tipDocumId = tipDocumId;
	}
	public String getCodDocum() {
		return codDocum;
	}
	public void setCodDocum(String codDocum) {
		this.codDocum = codDocum;
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
	public User getUsuarioId() {
		return usuarioId;
	}
	public void setUsuarioId(User usuarioId) {
		this.usuarioId = usuarioId;
	}
	public InstitucionDTO getInstitucionId() {
		return institucionId;
	}
	public void setInstitucionId(InstitucionDTO institucionId) {
		this.institucionId = institucionId;
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
