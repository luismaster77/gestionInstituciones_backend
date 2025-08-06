package com.co.lep.gestion.estudiantes.entity;

import java.io.Serializable;
import java.util.Date;

import com.co.lep.gestion.estudiantes.security.entity.User;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "docentes")
@Getter
@Setter
public class DocenteEntity implements Serializable{

	private static final long serialVersionUID = 463232347120776699L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	@Column(name = "nom_docente")
	private String nomDocente;
	@Column(name = "ape1_docente")
	private String ape1Docente;
	@Column(name = "ape2_docente")
	private String ape2Docente;
	
	@OneToOne
	@JoinColumn(name = "tipo_docum_id")
	private TipoDocumentoEntity tipoDocumId;
	
	@Column(name = "cod_docum")
	private String codDocum;
	@Column(name = "email")
	private String email;
	@Column(name = "celular")
	private String celular;
	
	@OneToOne
	@JoinColumn(name = "usuario_id")
	private User usuarioId;
	
	@OneToOne
	@JoinColumn(name = "institucion_id")
	private InstitucionEntity institucionId;
	@OneToOne
	@JoinColumn(name = "estado_id")
	private EstadoEntity estadoId;
	
	@Column(name = "fec_creacion")
	private Date fecCreacion;
	
	public String getNombreCompleto(){
    	return ((nomDocente != null ? nomDocente : "") + " " +
                (ape1Docente != null ? ape1Docente : "") + " " +
                (ape2Docente != null ? ape2Docente : ""));
    }
}
