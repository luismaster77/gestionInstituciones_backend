package com.co.flexicraftsolutions.gestion.estudiantes.exepciones;

public class RegistroNoEncontradoException extends RuntimeException {

	private static final long serialVersionUID = 5526636472327293388L;

	public RegistroNoEncontradoException(String message) {
        super(message);
    }
}
