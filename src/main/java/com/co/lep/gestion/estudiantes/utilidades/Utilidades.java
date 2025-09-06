package com.co.lep.gestion.estudiantes.utilidades;

import java.util.Date;

public class Utilidades {
	
	public static String convertirDateToString(Date fecha) {
		return fecha == null ? null : new java.text.SimpleDateFormat("dd-MM-yyyy").format(fecha);
	}
}
