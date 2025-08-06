package com.co.flexicraftsolutions.gestion.estudiantes.utilidades;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;

import com.co.flexicraftsolutions.gestion.estudiantes.dto.DocenteDTO;

public class LoginGenerator {
    private static final int CODE_LENGTH = 7;

    public static String generateUniqueLogin(String nombre, String apellido, List<String> loginsExistentes) {
    	
    	 String nombreLimpio = StringUtils.deleteWhitespace(nombre).toLowerCase();
         String apellidoLimpio = StringUtils.deleteWhitespace(apellido).toLowerCase();
    	
    	
        String loginPropuesto = generarLoginPropuesto(nombreLimpio, apellidoLimpio);
        if (!loginsExistentes.contains(loginPropuesto)) {
            return loginPropuesto;
        } else {
        	return agregarSufijoUnico(loginPropuesto, loginsExistentes);
        }
    }
    
    private static String agregarSufijoUnico(String loginPropuesto, List<String> loginsExistentes) {
    	String loginConSufijo = loginPropuesto + "-fcs";
        int contador = 1;
        while (loginsExistentes.contains(loginConSufijo)) {
            loginConSufijo = loginPropuesto + "-fcs" + contador;
            contador++;
        }
        return loginConSufijo;
	}

	private static String generarLoginPropuesto(String nombre, String apellido) {
        String loginPropuesto = (nombre.substring(0, Math.min(nombre.length(), 1)) + apellido);
        if (loginPropuesto.length() > CODE_LENGTH) {
            loginPropuesto = loginPropuesto.substring(0, 7);
        }
        return loginPropuesto;
    }
	
	public static List<String> generarLogins(DocenteDTO docenteDTO, List<String> codDocentes) {
		String nombre = docenteDTO.getNomDocente().split(" ")[0].toLowerCase().replaceAll("\\s+", "");
        String apellido = docenteDTO.getApe1Docente().split(" ")[0].toLowerCase().replaceAll("\\s+", "");

        Set<String> codDocentesSet = new HashSet<>(codDocentes); // Convertimos la lista a un set para una búsqueda más rápida
        List<String> loginsList = new ArrayList<>();
        
        // Generar 5 combinaciones base
        loginsList.add(generateUniqueLogin(nombre + apellido, codDocentesSet));
        loginsList.add(generateUniqueLogin(apellido + nombre, codDocentesSet));
        loginsList.add(generateUniqueLogin(nombre + apellido.substring(0, 1), codDocentesSet));
        loginsList.add(generateUniqueLogin(nombre.substring(0, 1) + apellido, codDocentesSet));
        loginsList.add(generateUniqueLogin(apellido + nombre.substring(0, 1), codDocentesSet));

        return loginsList;
    }
	
	 // Método para generar un login único
    private static String generateUniqueLogin(String baseLogin, Set<String> codDocentesSet) {
        String login = baseLogin;
        int counter = 1;

        // Si el login ya existe, añadir un sufijo numérico para hacerlo único
        while (codDocentesSet.contains(login)) {
            login = baseLogin + counter;
            counter++;
        }

        codDocentesSet.add(login); // Añadimos el nuevo login al set para evitar duplicados
        return login;
    }
}
