package com.co.lep.gestion.estudiantes.security.dto;

import com.co.lep.gestion.estudiantes.security.entity.User;

public class AuthenticationResponseWithUser {
    private String jwt;
    private boolean cambioPassword;
    private User usuarioInfo;

    public AuthenticationResponseWithUser(String jwt, boolean cambioPassword, User usuarioInfo) {
        this.jwt = jwt;
        this.cambioPassword = cambioPassword;
        this.usuarioInfo = usuarioInfo;
    }

    // Getters y setters
    public String getJwt() {
        return jwt;
    }

    public void setJwt(String jwt) {
        this.jwt = jwt;
    }

    public boolean isCambioPassword() {
		return cambioPassword;
	}

	public void setCambioPassword(boolean cambioPassword) {
		this.cambioPassword = cambioPassword;
	}

	public User getUsuarioInfo() {
		return usuarioInfo;
	}

	public void setUsuarioInfo(User usuarioInfo) {
		this.usuarioInfo = usuarioInfo;
	}
}

