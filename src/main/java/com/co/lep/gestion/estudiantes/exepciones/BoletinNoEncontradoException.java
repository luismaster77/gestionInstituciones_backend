package com.co.lep.gestion.estudiantes.exepciones;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class BoletinNoEncontradoException extends RuntimeException {

	private static final long serialVersionUID = 8688565211558938267L;

	public BoletinNoEncontradoException(String mensaje) {
        super(mensaje);
    }
}

