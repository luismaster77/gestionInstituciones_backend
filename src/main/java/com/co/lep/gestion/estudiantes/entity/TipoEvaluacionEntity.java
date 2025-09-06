package com.co.lep.gestion.estudiantes.entity;

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
@Table(name="tipo_evaluacion")
@Getter
@Setter
public class TipoEvaluacionEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "nom_evaluacion")
	private String nomEvaluacion;
	
	@ManyToOne
    @JoinColumn(name = "institucion_id")
    private InstitucionEntity institucionId;
}
