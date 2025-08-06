package com.co.lep.gestion.estudiantes.security.entity;

import java.io.Serializable;
import java.util.List;

import com.co.lep.gestion.estudiantes.menu.entity.MenuEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "role")
public class Role implements Serializable{

	private static final long serialVersionUID = 3902155596192468003L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="description")
	private String description;
	
	@Column(name="name")
	private String name;
	
	@ManyToMany
    @JoinTable(
    name = "role_menu",
    joinColumns = @JoinColumn(name = "role_id"),
    inverseJoinColumns = @JoinColumn(name = "menu_id"))
    private List<MenuEntity> menus;
}
