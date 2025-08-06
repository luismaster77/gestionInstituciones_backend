package com.co.lep.gestion.estudiantes.entity;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="institucion")
@Getter
@Setter
public class InstitucionEntity implements Serializable{

	private static final long serialVersionUID = -6284490807074862268L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="nom_institucion")
	private String nomInstitucion;
	
	@Column(name="cod_institucion")
	private String codInstitucion;
	
	@Column(name="nit")
	private Long nit;
	
	@Column(name="dane")
	private Long dane;
	
	@Column(name="direccion")
	private String direccion;
	
	@Column(name="email")
	private String email;
	
	@Column(name="descripcion")
	private String descripcion;
	
	@Column(name="logo")
	private String logo;

}
