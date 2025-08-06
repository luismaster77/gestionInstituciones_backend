package com.co.lep.gestion.estudiantes.utilidades;

import java.util.List;

public class Validador {

    public static boolean isEmpty(List<?> lista) {
        return lista == null || lista.isEmpty();
    }

    public static boolean cadenaEstaVaciaONula(String cadena) {
        return cadena == null || cadena.isEmpty() || cadena.equals("null");
    }

    public static boolean intEsNulo(Integer entero) {
        return entero == null;
    }

    public static boolean longEsNulo(Long largo) {
        return largo == null;
    }

    public static boolean floatEsNulo(Float flotante) {
        return flotante == null;
    }

    public static boolean objetoEsNulo(Object objeto) {
        return objeto == null;
    }
}

