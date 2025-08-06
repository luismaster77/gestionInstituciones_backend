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
@Table(name = "materias")
@Getter
@Setter
public class MateriaEntity implements Serializable{
	
	private static final long serialVersionUID = 5667554673515708737L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	@Column(name = "nom_materia" , unique = true)
	private String nomMateria;
	
	@ManyToOne
    @JoinColumn(name = "nivel_id" , unique = true)
	private NivelEntity nivelId;
	
	@ManyToOne
	@JoinColumn(name = "docente_id")
	private DocenteEntity docenteId;
	
	@ManyToOne
    @JoinColumn(name = "institucion_id")
    private InstitucionEntity institucionId;
	
	@Column(name = "intensidad_horas")
	private Integer intensidadHoras;
	
	@Column(name = "fec_creacion")
	private Date fecCreacion;
}
