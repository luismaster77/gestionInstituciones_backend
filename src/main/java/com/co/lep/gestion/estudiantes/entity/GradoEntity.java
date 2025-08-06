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
@Table(name = "grados")
@Getter
@Setter
public class GradoEntity implements Serializable {

	private static final long serialVersionUID = 4730398239906146477L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "nom_grado")
	private String nomGrado;

	@Column(name = "anio_electivo")
	private String anioElectivo;

	@ManyToOne
	@JoinColumn(name = "institucion_id")
	private InstitucionEntity institucionId;

	@ManyToOne
	@JoinColumn(name = "docente_Id")
	private DocenteEntity docenteId;

	@Column(name = "fec_creacion")
	private Date fecCreacion;
}
