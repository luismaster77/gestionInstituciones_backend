package com.co.lep.gestion.estudiantes.menu.entity;

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
@Getter
@Setter
@Table(name = "icons_app")
public class IconAppEntity implements Serializable{

	private static final long serialVersionUID = -4412403111916190799L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name =  "cod_icon")
    private String codIcon;
    
    @Column(name =  "nom_icon")
    private String nomIcon;
    
    @Column(name = "tipo_icon")
    private String tipoIcon;
}
