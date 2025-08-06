package com.co.lep.gestion.estudiantes.security.dto;

import java.io.Serializable;
import java.util.Date;

import com.co.lep.gestion.estudiantes.security.entity.User;

public class TokenDTO implements Serializable {

	private static final long serialVersionUID = -4487716374600397417L;

	private Long id;
	private String token;
	private Date expiradatetoken;
	private User usuario;
	private Date fecAcceso;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public Date getExpiradatetoken() {
		return expiradatetoken;
	}
	public void setExpiradatetoken(Date expiradatetoken) {
		this.expiradatetoken = expiradatetoken;
	}
	public Date getFecAcceso() {
		return fecAcceso;
	}
	public void setFecAcceso(Date fecAcceso) {
		this.fecAcceso = fecAcceso;
	}
	public User getUsuario() {
		return usuario;
	}
	public void setUsuario(User usuario) {
		this.usuario = usuario;
	}
}
