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
@Table(name = "periodo_electivo")
@Getter
@Setter
public class PeriodoElectivoEntity implements Serializable{

	private static final long serialVersionUID = -7902437771579857307L;
	
	@Id
	@GeneratedValue( strategy = GenerationType.IDENTITY )
	private Long id;
	
	@Column(name = "cod_periodo_elect")
	private String codPeriodoElect;
	
	@Column(name = "nom_periodo_elect")
	private String nomPeriodoElect;
	
	@ManyToOne
    @JoinColumn(name = "institucion_id")
    private InstitucionEntity institucionId;
	
	@Column(name = "fec_creacion")
	private Date fecCreacion;

}