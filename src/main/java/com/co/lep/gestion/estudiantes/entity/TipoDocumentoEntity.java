package com.co.lep.gestion.estudiantes.entity;

import java.io.Serializable;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "tipo_documentos")
@Getter
@Setter
public class TipoDocumentoEntity implements Serializable{

	private static final long serialVersionUID = -9144257060140989696L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	@Column(name = "cod_tipo_docum")
	private String codTipoDocum;
	
	@Column(name = "nom_tipo_docum")
	private String nomTipoDocum;
	
	@Column(name = "fec_creacion")
	private Date fecCreacion;
	
}
