package com.co.flexicraftsolutions.gestion.estudiantes.security.dto;

import java.io.Serializable;
import java.util.Date;

public class TokenDTO implements Serializable{

	private static final long serialVersionUID = -4487716374600397417L;
	
	private String token;
	private Date expiradatetoken;
	private Date fecAcceso;
	private String usuario;
	
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
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	
	
}
