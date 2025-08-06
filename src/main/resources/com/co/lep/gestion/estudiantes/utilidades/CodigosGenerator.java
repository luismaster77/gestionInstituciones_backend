package com.co.flexicraftsolutions.gestion.estudiantes.utilidades;

import java.util.List;

public class CodigosGenerator {

    public static String generateUniqueCodigo(String nombre, List<String> codigosList) {
    	
        String codigoPropuesto = generarCodigoPropuesto(nombre);
        
        if(codigosList.isEmpty()) {
        	return codigoPropuesto;
        }
        else if (!codigosList.contains(codigoPropuesto)) {
            return codigoPropuesto;
        } else {
        	return agregarSufijoUnico(codigoPropuesto, codigosList);
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

	private static String generarCodigoPropuesto(String nombre) {
        return nombre.substring(0, 3);
    }
}
