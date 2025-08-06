package com.co.lep.gestion.estudiantes.dto;

import java.io.Serializable;

public class RegistroDTO implements Serializable{

	private static final long serialVersionUID = -6932428510613126317L;
	
	private String codCampo;
    private String valCampo;
    private boolean isLastField;
    
	public String getCodCampo() {
		return codCampo;
	}
	public void setCodCampo(String codCampo) {
		this.codCampo = codCampo;
	}
	public String getValCampo() {
		return valCampo;
	}
	public void setValCampo(String valCampo) {
		this.valCampo = valCampo;
	}
	public boolean isLastField() {
		return isLastField;
	}
	public void setLastField(boolean isLastField) {
		this.isLastField = isLastField;
	}
}
