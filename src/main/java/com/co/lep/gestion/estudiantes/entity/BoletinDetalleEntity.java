package com.co.lep.gestion.estudiantes.entity;

import java.math.BigDecimal;
import java.text.DecimalFormat;
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
@Table(name = "boletin_detalle")
@Getter
@Setter
public class BoletinDetalleEntity implements Comparable<BoletinDetalleEntity>{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "boletin_id")
	private BoletinEntity boletinId;

	@ManyToOne
	@JoinColumn(name = "materia_id")
	private MateriaEntity materiaId;

	@Column(name = "nota",precision = 7, scale = 1)
	private BigDecimal nota;
	
	@Column(name = "escala_nacional")
	private String escalaNacional;
	
	@Column( name = "fec_creacion")
    private Date fecCreacion;
	
	@Override
	public int compareTo(BoletinDetalleEntity other) {
	   // Ordenar por nombreMateria de manera ascendente
	   return this.materiaId.getNomMateria().compareTo(other.materiaId.getNomMateria());
	}
	
	public String getNotaFormatted() {
        DecimalFormat df = new DecimalFormat("#.0");
        return df.format(nota);
    }

}
