package com.co.lep.gestion.estudiantes.exepciones;

public class PasswordResetException extends RuntimeException {

	private static final long serialVersionUID = -2257139558084480097L;

	public PasswordResetException(String message) {
        super(message);
    }
    
    public PasswordResetException(String message, Throwable cause) {
        super(message, cause);
    }
}

