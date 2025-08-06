package com.co.lep.gestion.estudiantes.entity;

import java.io.Serializable;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "institucion")
@Getter
@Setter
public class InstitucionEntity implements Serializable {

	private static final long serialVersionUID = -6284490807074862268L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "nom_institucion")
	private String nomInstitucion;

	@Column(name = "nit", unique = true)
	private Long nit;

	@Column(name = "dane")
	private Long dane;

	@Column(name = "direccion")
	private String direccion;

	@Column(name = "email" ,unique = true)
	private String email;

	@Column(name = "descripcion")
	private String descripcion;

	@Column(name = "logo")
	private String logo;

	@Column(name = "rector_institucion")
	private String rectorInstitucion;
	
	@ManyToOne
	@JoinColumn(name = "sede_id")
	private SedeEntity sedeId;
	
	@ManyToOne
	@JoinColumn(name = "ciudad_id")
	private CiudadEntity ciudadId;

	@Column(name = "fec_creacion")
	private Date fecCreacion;

}
