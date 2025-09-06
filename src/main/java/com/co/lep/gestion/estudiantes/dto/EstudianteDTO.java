package com.co.lep.gestion.estudiantes.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.co.lep.gestion.estudiantes.security.entity.User;

public class EstudianteDTO implements Serializable{
	
	private static final long serialVersionUID = -3758269476214196856L;
	
	private Long id;
    private TipoDocumentoDTO tipoDocumId;
    private String codDocum;
    private String nomEstudiante;
    private String ape1Estudiante;
    private String ape2Estudiante;
    private String celular;
    private String email;
    private InstitucionDTO institucionId;
    private User usuarioId;
    private GradoDTO gradoId;
    private EstadoDTO estadoId;
    private EstudiantesNotaDTO estudiantesNotaId;
    private Date fecCreacion;
    
    //campos temporales
    private String txtObservaciones;
    private BigDecimal nota;
    
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public TipoDocumentoDTO getTipoDocumId() {
		return tipoDocumId;
	}
	public void setTipoDocumId(TipoDocumentoDTO tipoDocumId) {
		this.tipoDocumId = tipoDocumId;
	}
	public String getCodDocum() {
		return codDocum;
	}
	public void setCodDocum(String codDocum) {
		this.codDocum = codDocum;
	}
	public String getNomEstudiante() {
		return nomEstudiante;
	}
	public void setNomEstudiante(String nomEstudiante) {
		this.nomEstudiante = nomEstudiante;
	}
	public String getApe1Estudiante() {
		return ape1Estudiante;
	}
	public void setApe1Estudiante(String ape1Estudiante) {
		this.ape1Estudiante = ape1Estudiante;
	}
	public String getApe2Estudiante() {
		return ape2Estudiante;
	}
	public void setApe2Estudiante(String ape2Estudiante) {
		this.ape2Estudiante = ape2Estudiante;
	}
	public String getCelular() {
		return celular;
	}
	public void setCelular(String celular) {
		this.celular = celular;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public InstitucionDTO getInstitucionId() {
		return institucionId;
	}
	public void setInstitucionId(InstitucionDTO institucionId) {
		this.institucionId = institucionId;
	}
	public User getUsuarioId() {
		return usuarioId;
	}
	public void setUsuarioId(User usuarioId) {
		this.usuarioId = usuarioId;
	}
	public GradoDTO getGradoId() {
		return gradoId;
	}
	public void setGradoId(GradoDTO gradoId) {
		this.gradoId = gradoId;
	}
	public EstadoDTO getEstadoId() {
		return estadoId;
	}
	public void setEstadoId(EstadoDTO estadoId) {
		this.estadoId = estadoId;
	}
	public EstudiantesNotaDTO getEstudiantesNotaId() {
		return estudiantesNotaId;
	}
	public void setEstudiantesNotaId(EstudiantesNotaDTO estudiantesNotaId) {
		this.estudiantesNotaId = estudiantesNotaId;
	}
	public Date getFecCreacion() {
		return fecCreacion;
	}
	public void setFecCreacion(Date fecCreacion) {
		this.fecCreacion = fecCreacion;
	}
	public String getTxtObservaciones() {
		return txtObservaciones;
	}
	public void setTxtObservaciones(String txtObservaciones) {
		this.txtObservaciones = txtObservaciones;
	}
	public BigDecimal getNota() {
		return nota;
	}
	public void setNota(BigDecimal nota) {
		this.nota = nota;
	}
}