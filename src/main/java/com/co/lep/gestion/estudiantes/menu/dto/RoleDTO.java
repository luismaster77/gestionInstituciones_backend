package com.co.lep.gestion.estudiantes.menu.dto;

import java.io.Serializable;
import java.util.List;

public class RoleDTO implements Serializable{
	
	private static final long serialVersionUID = 6981690127629896703L;
	
	private Long id;
	private String name;
	private String description;
	private List<MenuDTO> menus;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public List<MenuDTO> getMenus() {
		return menus;
	}
	public void setMenus(List<MenuDTO> menus) {
		this.menus = menus;
	}
}
