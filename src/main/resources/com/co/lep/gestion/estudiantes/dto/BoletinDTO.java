package com.co.flexicraftsolutions.gestion.estudiantes.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

public class BoletinDTO implements Serializable{
	
	private static final long serialVersionUID = 2313588540886868092L;
	
	private Long id;
	private EstudianteDTO estudiante;
	private BigDecimal nota;
	private MateriaDTO materia;
	private GradoDTO grado;
	private PeriodoElectivoDTO periodoElectivo;
	private InstitucionDTO institucion;
	private DocenteDTO docente;
	private List<MateriaDTO> materiasList;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public EstudianteDTO getEstudiante() {
		return estudiante;
	}
	public void setEstudiante(EstudianteDTO estudiante) {
		this.estudiante = estudiante;
	}
	public BigDecimal getNota() {
		return nota;
	}
	public void setNota(BigDecimal nota) {
		this.nota = nota;
	}
	public MateriaDTO getMateria() {
		return materia;
	}
	public void setMateria(MateriaDTO materia) {
		this.materia = materia;
	}
	public GradoDTO getGrado() {
		return grado;
	}
	public void setGrado(GradoDTO grado) {
		this.grado = grado;
	}
	public PeriodoElectivoDTO getPeriodoElectivo() {
		return periodoElectivo;
	}
	public void setPeriodoElectivo(PeriodoElectivoDTO periodoElectivo) {
		this.periodoElectivo = periodoElectivo;
	}
	public InstitucionDTO getInstitucion() {
		return institucion;
	}
	public void setInstitucion(InstitucionDTO institucion) {
		this.institucion = institucion;
	}
	public DocenteDTO getDocente() {
		return docente;
	}
	public void setDocente(DocenteDTO docente) {
		this.docente = docente;
	}
	public List<MateriaDTO> getMateriasList() {
		return materiasList;
	}
	public void setMateriasList(List<MateriaDTO> materiasList) {
		this.materiasList = materiasList;
	}
}
