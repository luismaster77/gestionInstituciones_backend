package com.co.lep.gestion.estudiantes.menu.entity;

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
@Table(name = "menu_app")
@Getter
@Setter
public class MenuEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "id_opcion")
    private String idOpcion;

    @Column(name = "label")
    private String label;

    @ManyToOne
	@JoinColumn(name = "icon")
    private IconAppEntity icon;

    @ManyToOne
	@JoinColumn(name = "items")
    private ItemMenuEntity items;

    @Column(name = "descripcion")
    private String descripcion;
    
    @Column(name = "clase")
    private String clase;
    
    @Column(name = "orden_menu")
    private Integer ordenMenu;
}

