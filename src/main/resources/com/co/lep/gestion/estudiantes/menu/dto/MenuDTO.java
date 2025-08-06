package com.co.flexicraftsolutions.gestion.estudiantes.menu.dto;

import java.io.Serializable;
import java.util.List;

public class MenuDTO implements Serializable {
	
	private static final long serialVersionUID = -5388831119626079751L;
	private Long id;
	private String idOpcion;
	private String label;
	private String icon;
	private String descripcion;
	private List<ItemMenuDTO> items;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getIdOpcion() {
		return idOpcion;
	}
	public void setIdOpcion(String idOpcion) {
		this.idOpcion = idOpcion;
	}
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public List<ItemMenuDTO> getItems() {
		return items;
	}
	public void setItems(List<ItemMenuDTO> items) {
		this.items = items;
	}
}
