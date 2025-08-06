package com.co.lep.gestion.estudiantes.dto;

import java.io.Serializable;

public class BoletinConsultaDTO implements Serializable{
	
	private static final long serialVersionUID = -8740755336188852018L;
	
	private EstudianteDTO estudianteId;
	private GradoDTO gradoId;
	private PeriodoElectivoDTO periodoElectivoId;
	
	public EstudianteDTO getEstudianteId() {
		return estudianteId;
	}
	public void setEstudianteId(EstudianteDTO estudianteId) {
		this.estudianteId = estudianteId;
	}
	public GradoDTO getGradoId() {
		return gradoId;
	}
	public void setGradoId(GradoDTO gradoId) {
		this.gradoId = gradoId;
	}
	public PeriodoElectivoDTO getPeriodoElectivoId() {
		return periodoElectivoId;
	}
	public void setPeriodoElectivoId(PeriodoElectivoDTO periodoElectivoId) {
		this.periodoElectivoId = periodoElectivoId;
	}
}