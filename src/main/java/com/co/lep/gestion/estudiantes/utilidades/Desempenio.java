package com.co.lep.gestion.estudiantes.utilidades;

public enum  Desempenio {
	SUPERIOR("Superior"),
	ALTO("Alto"),
	BASICO("Básico"),
	BAJO("Bajo");

	private String descripcion;

	Desempenio(String descripcion) {
	        this.descripcion = descripcion;
	    }

	public String getDescripcion() {
		return descripcion;
	}
}
