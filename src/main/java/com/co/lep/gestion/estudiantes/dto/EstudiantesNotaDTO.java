package com.co.lep.gestion.estudiantes.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.co.lep.gestion.estudiantes.entity.GradoEntity;
import com.co.lep.gestion.estudiantes.entity.MateriaEntity;
import com.co.lep.gestion.estudiantes.entity.PeriodoElectivoEntity;
import com.co.lep.gestion.estudiantes.entity.TipoEvaluacionEntity;
import com.co.lep.gestion.estudiantes.security.entity.User;

public class EstudiantesNotaDTO implements Serializable {
	
	private static final long serialVersionUID = 8231003908500012670L;
	private Long id;
    private MateriaEntity materiaId;
    private GradoEntity gradoId;
    private PeriodoElectivoEntity periodoElectivoId;
    private TipoEvaluacionEntity tipoEvaluacionId;
    private EstudianteDTO estudianteId;
    private String nomEvaluacion;
    private User usuarioId;
    private Date fecEvaluacion;
	private List<EstudianteDTO> estudiantes;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public MateriaEntity getMateriaId() {
		return materiaId;
	}
	public void setMateriaId(MateriaEntity materiaId) {
		this.materiaId = materiaId;
	}
	public GradoEntity getGradoId() {
		return gradoId;
	}
	public void setGradoId(GradoEntity gradoId) {
		this.gradoId = gradoId;
	}
	public PeriodoElectivoEntity getPeriodoElectivoId() {
		return periodoElectivoId;
	}
	public void setPeriodoElectivoId(PeriodoElectivoEntity periodoElectivoId) {
		this.periodoElectivoId = periodoElectivoId;
	}
	public TipoEvaluacionEntity getTipoEvaluacionId() {
		return tipoEvaluacionId;
	}
	public void setTipoEvaluacionId(TipoEvaluacionEntity tipoEvaluacionId) {
		this.tipoEvaluacionId = tipoEvaluacionId;
	}
	public EstudianteDTO getEstudianteId() {
		return estudianteId;
	}
	public void setEstudianteId(EstudianteDTO estudianteId) {
		this.estudianteId = estudianteId;
	}
	public String getNomEvaluacion() {
		return nomEvaluacion;
	}
	public void setNomEvaluacion(String nomEvaluacion) {
		this.nomEvaluacion = nomEvaluacion;
	}
	public User getUsuarioId() {
		return usuarioId;
	}
	public void setUsuarioId(User usuarioId) {
		this.usuarioId = usuarioId;
	}
	public Date getFecEvaluacion() {
		return fecEvaluacion;
	}
	public void setFecEvaluacion(Date fecEvaluacion) {
		this.fecEvaluacion = fecEvaluacion;
	}
	public List<EstudianteDTO> getEstudiantes() {
		return estudiantes;
	}
	public void setEstudiantes(List<EstudianteDTO> estudiantes) {
		this.estudiantes = estudiantes;
	}
}