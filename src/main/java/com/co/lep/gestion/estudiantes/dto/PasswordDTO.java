package com.co.lep.gestion.estudiantes.dto;

import java.io.Serializable;

public class PasswordDTO implements Serializable {
	
	private static final long serialVersionUID = -3814101901046656834L;
	
	private String constraseniaActual;
	private String nuevaContrasenia;
	private String confirmarContrasenia;
	private String username;
	
	public String getConstraseniaActual() {
		return constraseniaActual;
	}
	public void setConstraseniaActual(String constraseniaActual) {
		this.constraseniaActual = constraseniaActual;
	}
	public String getNuevaContrasenia() {
		return nuevaContrasenia;
	}
	public void setNuevaContrasenia(String nuevaContrasenia) {
		this.nuevaContrasenia = nuevaContrasenia;
	}
	public String getConfirmarContrasenia() {
		return confirmarContrasenia;
	}
	public void setConfirmarContrasenia(String confirmarContrasenia) {
		this.confirmarContrasenia = confirmarContrasenia;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
}