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
@Table(name="estudiante_nota")
@Getter
@Setter
public class EstudianteNotasEntity implements Serializable{
	
	private static final long serialVersionUID = 1488769046259013154L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

	@ManyToOne
    @JoinColumn(name = "materia_id")
    private MateriaEntity materiaId;
	
	@ManyToOne
    @JoinColumn(name = "grado_id")
    private GradoEntity gradoId;
	
	@ManyToOne
    @JoinColumn(name = "periodo_electivo_id")
    private PeriodoElectivoEntity periodoElectivoId;
	
	@ManyToOne
    @JoinColumn(name = "tipo_evaluacion_id")
    private TipoEvaluacionEntity tipoEvaluacionId;
	
	@Column( name = "nom_evaluacion")
    private String nomEvaluacion;
	
    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private User usuarioId;
    
    @Column(name = "fec_evaluacion")
    private Date fecEvaluacion;
}
