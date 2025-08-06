package com.co.lep.gestion.estudiantes.exepciones;

public class RegistroNoGuardadoException extends RuntimeException {

	private static final long serialVersionUID = 5526636472327293388L;

	public RegistroNoGuardadoException(String message) {
        super(message);
    }
}
