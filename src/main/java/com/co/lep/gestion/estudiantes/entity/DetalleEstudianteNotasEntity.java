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
@Table(name="detalle_evaluacion_estudiante")
@Getter
@Setter
public class DetalleEstudianteNotasEntity implements Serializable{
	
	private static final long serialVersionUID = 1488769046259013154L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

	@ManyToOne
    @JoinColumn(name = "estudiante_nota_id")
    private EstudianteNotasEntity estudianteNotasId;
	
	@ManyToOne
    @JoinColumn(name = "estudiante_id")
    private EstudianteEntity estudianteId;
    
	@Column(name = "nota",precision = 7, scale = 1)
	private BigDecimal nota;
	 
    @Column( name = "txt_observaciones")
    private String txtObservaciones;
}
