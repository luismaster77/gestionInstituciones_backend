package com.co.flexicraftsolutions.gestion.estudiantes.menu.dto;

import java.io.Serializable;

public class ItemMenuDTO implements Serializable{

	private static final long serialVersionUID = 8284154952345864502L;
	
	private Long id;
	private String label;
	private String icon;
	private String routerLink;

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
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
	public String getRouterLink() {
		return routerLink;
	}
	public void setRouterLink(String routerLink) {
		this.routerLink = routerLink;
	}
}