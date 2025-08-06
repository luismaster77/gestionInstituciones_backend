package com.co.lep.gestion.estudiantes.exepciones;

public class PasswordMismatchException extends RuntimeException {
	private static final long serialVersionUID = 1L;

    public PasswordMismatchException() {
        super("Las contraseñas no coinciden.");
    }

    public PasswordMismatchException(String message) {
        super(message);
    }
}
