package com.co.flexicraftsolutions.gestion.estudiantes.entity;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "estados")
@Getter
@Setter
public class EstadoEntity implements Serializable{
	
	private static final long serialVersionUID = 7596363134495049921L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

	@Column(name = "cod_campo")
	private String codCampo;
	@Column(name = "cod_valor")
	private String codValor;
	@Column(name = "nom_valor")
	private String nomValor;
	
}
