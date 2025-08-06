package com.co.lep.gestion.estudiantes.exepciones;

public class TokenNotFoundException extends RuntimeException {
	private static final long serialVersionUID = 4575748527983334680L;

	public TokenNotFoundException(String message) {
        super(message);
    }
    
    public TokenNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
