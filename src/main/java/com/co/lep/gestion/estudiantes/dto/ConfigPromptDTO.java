package com.co.lep.gestion.estudiantes.dto;

import java.io.Serializable;

import com.co.lep.gestion.estudiantes.security.entity.User;

public class ConfigPromptDTO implements Serializable{
	
	private static final long serialVersionUID = -3988842939859794701L;
	
	private Long id;
	private String txtPrompt;
	private User usuarioId;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTxtPrompt() {
		return txtPrompt;
	}
	public void setTxtPrompt(String txtPrompt) {
		this.txtPrompt = txtPrompt;
	}
	public User getUsuarioId() {
		return usuarioId;
	}
	public void setUsuarioId(User usuarioId) {
		this.usuarioId = usuarioId;
	}
}
