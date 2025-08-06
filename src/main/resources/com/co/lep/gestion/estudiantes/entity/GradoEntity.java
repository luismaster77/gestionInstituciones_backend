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
@Table(name = "grado")
@Getter
@Setter
public class GradoEntity implements Serializable{
	
	private static final long serialVersionUID = 4730398239906146477L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	@Column(name = "nom_grado")
	private String nomGrado;
	@Column(name = "cod_grado")
	private String codGrado;
	@Column(name = "anio_electivo")
	private String anioElectivo;
	
}
