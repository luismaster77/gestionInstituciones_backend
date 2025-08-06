package com.co.lep.gestion.estudiantes.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.co.lep.gestion.estudiantes.security.entity.User;

public class BoletinDTO implements Serializable{
	
	private static final long serialVersionUID = 2313588540886868092L;
	
	private Long id;
	private EstudianteDTO estudianteId;
	private GradoDTO gradoId;
	private InstitucionDTO institucionId;
	private DocenteDTO docenteId;
	private User usuarioId;
	private PeriodoElectivoDTO periodoElectivoId;
	private String txtObservaciones;
	private Integer numHorasNoAsistidas;
	private GradoDTO promovidoGradoId;
	private String convivenciaEscolar;
	private BigDecimal notaConvivenciaEscolar;
	private boolean aprobado;
	private Integer nroPuestoEstudiante;
	private List<MateriaDTO> materiasList;
	private List<BoletinDetalleDTO> materiasDetalleList;
	private Date fecCreacion;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
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
	public InstitucionDTO getInstitucionId() {
		return institucionId;
	}
	public void setInstitucionId(InstitucionDTO institucionId) {
		this.institucionId = institucionId;
	}
	public DocenteDTO getDocenteId() {
		return docenteId;
	}
	public void setDocenteId(DocenteDTO docenteId) {
		this.docenteId = docenteId;
	}
	public User getUsuarioId() {
		return usuarioId;
	}
	public void setUsuarioId(User usuarioId) {
		this.usuarioId = usuarioId;
	}
	public PeriodoElectivoDTO getPeriodoElectivoId() {
		return periodoElectivoId;
	}
	public void setPeriodoElectivoId(PeriodoElectivoDTO periodoElectivoId) {
		this.periodoElectivoId = periodoElectivoId;
	}
	public String getTxtObservaciones() {
		return txtObservaciones;
	}
	public void setTxtObservaciones(String txtObservaciones) {
		this.txtObservaciones = txtObservaciones;
	}
	public Integer getNumHorasNoAsistidas() {
		return numHorasNoAsistidas;
	}
	public void setNumHorasNoAsistidas(Integer numHorasNoAsistidas) {
		this.numHorasNoAsistidas = numHorasNoAsistidas;
	}
	public GradoDTO getPromovidoGradoId() {
		return promovidoGradoId;
	}
	public void setPromovidoGradoId(GradoDTO promovidoGradoId) {
		this.promovidoGradoId = promovidoGradoId;
	}
	public String getConvivenciaEscolar() {
		return convivenciaEscolar;
	}
	public void setConvivenciaEscolar(String convivenciaEscolar) {
		this.convivenciaEscolar = convivenciaEscolar;
	}
	public BigDecimal getNotaConvivenciaEscolar() {
		return notaConvivenciaEscolar;
	}
	public void setNotaConvivenciaEscolar(BigDecimal notaConvivenciaEscolar) {
		this.notaConvivenciaEscolar = notaConvivenciaEscolar;
	}
	public boolean isAprobado() {
		return aprobado;
	}
	public void setAprobado(boolean aprobado) {
		this.aprobado = aprobado;
	}
	public Integer getNroPuestoEstudiante() {
		return nroPuestoEstudiante;
	}
	public void setNroPuestoEstudiante(Integer nroPuestoEstudiante) {
		this.nroPuestoEstudiante = nroPuestoEstudiante;
	}
	public List<MateriaDTO> getMateriasList() {
		return materiasList;
	}
	public void setMateriasList(List<MateriaDTO> materiasList) {
		this.materiasList = materiasList;
	}
	public List<BoletinDetalleDTO> getMateriasDetalleList() {
		return materiasDetalleList;
	}
	public void setMateriasDetalleList(List<BoletinDetalleDTO> materiasDetalleList) {
		this.materiasDetalleList = materiasDetalleList;
	}
	public Date getFecCreacion() {
		return fecCreacion;
	}
	public void setFecCreacion(Date fecCreacion) {
		this.fecCreacion = fecCreacion;
	}
}