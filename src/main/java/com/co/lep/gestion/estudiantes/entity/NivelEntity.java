package com.co.lep.gestion.estudiantes.entity;

import java.io.Serializable;

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
@Table(name = "niveles")
@Getter
@Setter
public class NivelEntity implements Serializable{
	
	private static final long serialVersionUID = 8322536873123230117L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	@Column(name = "cod_nivel")
	private String codNivel;
	
	@Column(name = "nom_nivel")
	private String nomNivel;
	
	@ManyToOne
    @JoinColumn(name = "institucion_id")
    private InstitucionEntity institucionId;
	
}
