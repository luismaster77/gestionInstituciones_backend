package com.co.lep.gestion.estudiantes.security.dto;

import java.io.Serializable;
import java.util.Date;

import com.co.lep.gestion.estudiantes.dto.EstadoDTO;
import com.co.lep.gestion.estudiantes.dto.InstitucionDTO;
import com.co.lep.gestion.estudiantes.dto.TipoDocumentoDTO;
import com.co.lep.gestion.estudiantes.security.entity.Role;
public class UsuarioDTO implements Serializable{
	
	private static final long serialVersionUID = 8814187476066131099L;
	
	private Long id;
    private TipoDocumentoDTO tipoDocumId;
    private String codDocum;
	private String primerNombre;
	private String primerApellido;
	private String celular;
	private String email;
	private String username;
	private String password;
	private String nuevaPassword;
	private EstadoDTO estadoId;
	private InstitucionDTO institucionId;
	private Role roleId;
	private Boolean cambioPassword;
	private Date fecCreacion;
	
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
	public String getPrimerNombre() {
		return primerNombre;
	}
	public void setPrimerNombre(String primerNombre) {
		this.primerNombre = primerNombre;
	}
	public String getPrimerApellido() {
		return primerApellido;
	}
	public void setPrimerApellido(String primerApellido) {
		this.primerApellido = primerApellido;
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
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getNuevaPassword() {
		return nuevaPassword;
	}
	public void setNuevaPassword(String nuevaPassword) {
		this.nuevaPassword = nuevaPassword;
	}
	public EstadoDTO getEstadoId() {
		return estadoId;
	}
	public void setEstadoId(EstadoDTO estadoId) {
		this.estadoId = estadoId;
	}
	public InstitucionDTO getInstitucionId() {
		return institucionId;
	}
	public void setInstitucionId(InstitucionDTO institucionId) {
		this.institucionId = institucionId;
	}
	public Role getRoleId() {
		return roleId;
	}
	public void setRoleId(Role roleId) {
		this.roleId = roleId;
	}
	public Boolean getCambioPassword() {
		return cambioPassword;
	}
	public void setCambioPassword(Boolean cambioPassword) {
		this.cambioPassword = cambioPassword;
	}
	public Date getFecCreacion() {
		return fecCreacion;
	}
	public void setFecCreacion(Date fecCreacion) {
		this.fecCreacion = fecCreacion;
	}
}