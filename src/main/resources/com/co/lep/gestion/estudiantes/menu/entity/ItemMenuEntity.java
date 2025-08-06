package com.co.flexicraftsolutions.gestion.estudiantes.menu.entity;

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
@Table(name = "item_menu_app")
@Getter
@Setter
public class ItemMenuEntity {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="label")
    private String label;

    @ManyToOne
	@JoinColumn(name = "icon")
    private IconAppEntity icon;

    @Column(name="router_link")
    private String routerLink;
}
