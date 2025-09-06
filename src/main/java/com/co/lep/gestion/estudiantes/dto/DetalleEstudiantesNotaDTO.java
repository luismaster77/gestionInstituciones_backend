package com.co.lep.gestion.estudiantes.dto;

import java.io.Serializable;
import java.math.BigDecimal;

public class DetalleEstudiantesNotaDTO implements Serializable{
	
	private static final long serialVersionUID = -2581619053194702857L;
	private Long id;
    private EstudiantesNotaDTO estudiantesNotaId;
    private EstudianteDTO estudianteId;
	private BigDecimal nota;
    private String txtObservaciones;
    
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public EstudiantesNotaDTO getEstudiantesNotaId() {
		return estudiantesNotaId;
	}
	public void setEstudiantesNotaId(EstudiantesNotaDTO estudiantesNotaId) {
		this.estudiantesNotaId = estudiantesNotaId;
	}
	public EstudianteDTO getEstudianteId() {
		return estudianteId;
	}
	public void setEstudianteId(EstudianteDTO estudianteId) {
		this.estudianteId = estudianteId;
	}
	public BigDecimal getNota() {
		return nota;
	}
	public void setNota(BigDecimal nota) {
		this.nota = nota;
	}
	public String getTxtObservaciones() {
		return txtObservaciones;
	}
	public void setTxtObservaciones(String txtObservaciones) {
		this.txtObservaciones = txtObservaciones;
	}
}
