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
@Table(name = "sedes")
public class SedeEntity implements Serializable{
	
	private static final long serialVersionUID = 5980777407025462172L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "nom_sede")
	private String nomSede;
	
	@Column(name = "desc_sede")
	private String descSede;
	
	@Column(name = "dane")
	private String dane;
	
	@Column(name = "direccion")
	private String direccion;
}
