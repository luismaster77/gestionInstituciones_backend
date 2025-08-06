package com.co.lep.gestion.estudiantes.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "configuracion_adicional")
@Getter
@Setter
public class ListaValorEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	@Column(name = "cod_campo")
	private String codCampo;
	
	@Column(name = "cod_item")
	private String codItem;
	
	@Column(name = "nom_item")
	private String nomItem;
}
