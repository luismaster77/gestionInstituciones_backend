package com.co.lep.gestion.estudiantes.utilidades;

import java.util.List;

import com.co.lep.gestion.estudiantes.constantes.Constantes;


public class CodigosEstudiantesGenerator {

    public static String generateUniqueCodigoEstudiante(String codDocumEstudiante, List<String> codigosEstudiantes) {
    	
        String codigoPropuesto = generarCodigoPropuesto(codDocumEstudiante);
        if (!codigosEstudiantes.contains(codigoPropuesto)) {
            return codigoPropuesto;
        } else {
        	return agregarSufijoUnico(codigoPropuesto, codigosEstudiantes);
        }
    }
    
    private static String agregarSufijoUnico(String codigoPropuesto, List<String> codigosExistentes) {
    	String codigoConSufijo = codigoPropuesto;
        int contador = 1;
        while (codigosExistentes.contains(codigoConSufijo)) {
        	codigoConSufijo = codigoPropuesto + contador;
            contador++;
        }
        return codigoConSufijo;
	}

	private static String generarCodigoPropuesto(String codDocum) {
        return Constantes.PREFIX_ESTUDIANTE+codDocum;
    }
}
