package com.co.flexicraftsolutions.gestion.estudiantes.entity;

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
@Table(name="estudiantes")
@Getter
@Setter
public class EstudianteEntity implements Serializable{
	
	private static final long serialVersionUID = 3079495724891252560L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	@Column(name = "cod_estudiante", unique = true)
	private String codEstudiante;

    @Column(name = "cod_docum")
    private String codDocum;

    @Column(name = "nom_estudiante")
    private String nomEstudiante;

    @Column(name = "ape1_estudiante")
    private String ape1Estudiante;

    @Column(name = "ape2_estudiante")
    private String ape2Estudiante;

    @Column(name = "cod_docente")
    private String codDocente;

    @Column(name = "celular")
    private String celular;

    @Column(name = "email")
    private String email;
  
    @ManyToOne
    @JoinColumn(name = "grado_id")
    private GradoEntity gradoId;
    
    @ManyToOne
    @JoinColumn(name = "estado_id")
    private EstadoEntity estadoId;
    
    @ManyToOne
    @JoinColumn(name = "tipo_docum_id")
    private TipoDocumentoEntity tipoDocumId;

    @Column(name = "fec_registro")
    private Date fecRegistro;
}
