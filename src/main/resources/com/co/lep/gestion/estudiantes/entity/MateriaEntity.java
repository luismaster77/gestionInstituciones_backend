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
@Table(name = "materias")
@Getter
@Setter
public class MateriaEntity implements Serializable{
	
	private static final long serialVersionUID = 5667554673515708737L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	@Column(name = "nom_materia")
	private String nomMateria;
	@Column(name = "cod_materia", unique = true)
	private String codMateria;
	
	@ManyToOne
    @JoinColumn(name = "nivel_id")
	private NivelEntity nivelId;
	
	@ManyToOne
	@JoinColumn(name = "docente_id")
	private DocenteEntity docenteId;
}
