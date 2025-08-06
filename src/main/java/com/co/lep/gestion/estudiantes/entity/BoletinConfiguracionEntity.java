package com.co.lep.gestion.estudiantes.entity;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "boletin_configuracion")
public class BoletinConfiguracionEntity {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre_campo")
    private String nombreCampo;
    
    @Column(name = "id_campo")
    private String idCampo;

    @Column(name = "tipo_campo")
    private String tipoCampo;

    @Column(name = "obligatorio")
    private Boolean obligatorio;

    @Column(name = "orden")
    private Integer orden;

    @Column(name = "visible")
    private Boolean visible;
    
    @ManyToOne
    @JoinColumn(name = "institucion_id")
    private InstitucionEntity institucionId;

    @Column(name = "fec_creacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecCreacion = new Date();
}
