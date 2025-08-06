package com.co.lep.gestion.estudiantes.exepciones;

public class TokenExpiredException extends RuntimeException {
   
	private static final long serialVersionUID = -2120432172780730858L;

	public TokenExpiredException(String message) {
        super(message);
    }
    
    public TokenExpiredException(String message, Throwable cause) {
        super(message, cause);
    }
}

