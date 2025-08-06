package com.co.flexicraftsolutions.gestion.estudiantes.entity;

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
@Table(name = "materiaGrado")
@Getter
@Setter
public class MateriaGradoEntity implements Serializable{

	private static final long serialVersionUID = 5733217493274516529L;
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	@ManyToOne
    @JoinColumn(name = "id_materia")
	private MateriaEntity idMateria;
	
	@ManyToOne
    @JoinColumn(name = "id_grado")
	private GradoEntity idGrado;
	
	@ManyToOne
    @JoinColumn(name = "id_docente")
	private DocenteEntity idDocente;
	
}
