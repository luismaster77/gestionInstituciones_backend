package com.co.flexicraftsolutions.gestion.estudiantes.exepciones;

import org.springframework.http.HttpStatus;

public class ErrorResponse {
    private String mensaje;
    private HttpStatus estado;
    
	public ErrorResponse(String mensaje, HttpStatus estado) {
		super();
		this.mensaje = mensaje;
		this.estado = estado;
	}
	public String getMensaje() {
		return mensaje;
	}
	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}
	public HttpStatus getEstado() {
		return estado;
	}
	public void setEstado(HttpStatus estado) {
		this.estado = estado;
	}
}

