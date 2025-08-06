package com.co.lep.gestion.estudiantes.entity;

import java.io.Serializable;
import java.math.BigDecimal;
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
@Table(name = "boletin")
@Getter
@Setter
public class BoletinEntity implements Serializable{

	private static final long serialVersionUID = 5365459054439966551L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "estudiante_id")
	private EstudianteEntity estudianteId;
    
    @ManyToOne
	@JoinColumn(name = "grado_id")	
	private GradoEntity gradoId;
    
    @ManyToOne
    @JoinColumn(name = "institucion_id")
    private InstitucionEntity institucionId;
    
    @ManyToOne
    @JoinColumn(name = "docente_id")
    private DocenteEntity docenteId;

    @ManyToOne
   	@JoinColumn(name = "periodo_electivo_id")
	private PeriodoElectivoEntity periodoElectivoId;
    
    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private User usuarioId;
    
    @Column( name = "txt_observaciones")
    private String txtObservaciones;
    
    @Column( name = "num_horas_no_asistidas")
    private Integer numHorasNoAsistidas;
    
    @ManyToOne
    @JoinColumn(name = "promovido_grado_id") 
    private GradoEntity promovidoGradoId;
    
    @Column(name = "aprobado")
    private boolean aprobado;
    
    @Column(name = "convivencia_escolar",precision = 7, scale = 1)
    private String convivenciaEscolar;
    
    @Column(name = "nota_convivencia_escolar")
    private BigDecimal notaConvivenciaEscolar;
    
    @Column(name = "nro_puesto_estudiante")
    private Integer nroPuestoEstudiante;
    
    @Column( name = "fec_creacion")
    private Date fecCreacion;
}