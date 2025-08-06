package com.co.lep.gestion.estudiantes.entity;

import java.io.Serializable;
import java.math.BigDecimal;

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
@Table(name = "boletines")
@Getter
@Setter
public class BoletinEntity implements Serializable{

	private static final long serialVersionUID = 5365459054439966551L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "estudiante_id")
	private EstudianteEntity estudiante;
    
    @Column(name = "nota")
	private BigDecimal nota;
    
    @ManyToOne
	@JoinColumn(name = "materia_id")
	private MateriaEntity materia;
    
    @ManyToOne
	@JoinColumn(name = "grado_id")
	private GradoEntity grado;
    
    @ManyToOne
   	@JoinColumn(name = "periodo_electivo_id")
	private PeriodoElectivoEntity periodoElectivo;
    
    @ManyToOne
   	@JoinColumn(name = "institucion_id")
	private InstitucionEntity institucion;
    
    @ManyToOne
   	@JoinColumn(name = "docente_id")
	private DocenteEntity docente;
}