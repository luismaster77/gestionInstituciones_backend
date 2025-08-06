package com.co.lep.gestion.estudiantes.entity;

import java.io.Serializable;

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
@Table(name = "grado_materias")
@Getter
@Setter
public class GradoMateriasEntity implements Serializable{

	private static final long serialVersionUID = 5733217493274516529L;
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	@ManyToOne
	@JoinColumn(name = "grado_id")
	private GradoEntity gradoId;

	@ManyToOne
    @JoinColumn(name = "materia_id")
	private MateriaEntity materiaId;
		
	@ManyToOne
    @JoinColumn(name = "institucion_id")
	private InstitucionEntity institucionId;
}
