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
@Getter
@Setter
@Table(name = "ciudades")
public class CiudadEntity implements Serializable{
	
	private static final long serialVersionUID = 6689145204961629386L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "cod_pais")
	private String codPais;
	
	@Column(name = "municipio")
	private String municipio;
	
	@Column(name = "dpto")
	private String dpto;
	
	@Column(name = "pais")
	private String pais;

	public String getNomCompletoCiudad(){
		return (municipio + " - " + dpto + " - " + pais).toUpperCase();
	}
}