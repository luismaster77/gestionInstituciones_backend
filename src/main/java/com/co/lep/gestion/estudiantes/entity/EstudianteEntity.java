package com.co.lep.gestion.estudiantes.entity;

import java.io.Serializable;
import java.util.Date;

import com.co.lep.gestion.estudiantes.security.entity.User;

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

	@ManyToOne
    @JoinColumn(name = "tipo_docum_id")
    private TipoDocumentoEntity tipoDocumId;
	
    @Column(name = "cod_docum", unique = true)
    private String codDocum;

    @Column(name = "nom_estudiante")
    private String nomEstudiante;

    @Column(name = "ape1_estudiante")
    private String ape1Estudiante;

    @Column(name = "ape2_estudiante")
    private String ape2Estudiante;

    @Column(name = "celular")
    private String celular;

    @Column(name = "email")
    private String email;
    
    @ManyToOne
    @JoinColumn(name = "institucion_id")
    private InstitucionEntity institucionId;
    
    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private User usuarioId;
  
    @ManyToOne
    @JoinColumn(name = "grado_id")
    private GradoEntity gradoId;
    
    @ManyToOne
    @JoinColumn(name = "estado_id")
    private EstadoEntity estadoId;

    @Column(name = "fec_creacion")
    private Date fecCreacion;
    
    public String getNombreCompleto(){
    	return ((nomEstudiante != null ? nomEstudiante : "") + " " +
                (ape1Estudiante != null ? ape1Estudiante : "") + " " +
                (ape2Estudiante != null ? ape2Estudiante : "")).toUpperCase();
    }
}
