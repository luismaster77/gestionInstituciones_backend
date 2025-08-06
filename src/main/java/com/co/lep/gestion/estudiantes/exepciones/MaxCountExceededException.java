package com.co.lep.gestion.estudiantes.exepciones;

public class MaxCountExceededException extends RuntimeException {

	private static final long serialVersionUID = 7332187951193972119L;

	public MaxCountExceededException(String message) {
        super(message);
    }
    
    public MaxCountExceededException(String message, Throwable cause) {
        super(message, cause);
    }
}

