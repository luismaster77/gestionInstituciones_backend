package com.co.lep.gestion.estudiantes.utilidades;

import java.math.BigDecimal;
import java.util.List;

public class Validador {

    public static boolean isEmpty(List<?> lista) {
        return lista == null || lista.isEmpty();
    }

    public static boolean esNuloOVacio(Object valor) {
        if (valor == null) {
            return true;
        }
        if (valor instanceof String) {
            return ((String) valor).trim().isEmpty() || "null".equalsIgnoreCase((String) valor);
        }
        if (valor instanceof BigDecimal) {
            return ((BigDecimal) valor).compareTo(BigDecimal.ZERO) == 0;
        }
        if (valor instanceof Float) {
            return ((Float) valor).equals(0.0f);
        }
        return false;
    }


    public static boolean objetoEsNulo(Object objeto) {
        return objeto == null;
    }
}

