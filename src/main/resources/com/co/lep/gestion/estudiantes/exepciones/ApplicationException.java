package com.co.flexicraftsolutions.gestion.estudiantes.exepciones;

public class ApplicationException extends RuntimeException {

	private static final long serialVersionUID = 5059225562453106352L;

	public ApplicationException(String message, Throwable cause) {
        super(message, cause);
    }
}

