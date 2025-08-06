package com.co.flexicraftsolutions.gestion.estudiantes.utilidades;

import java.io.Serializable;
import org.springframework.stereotype.Service;
import com.co.flexicraftsolutions.gestion.estudiantes.dto.DocenteDTO;

@Service
public class UserLoginApp implements Serializable{
	
	private static final long serialVersionUID = 4720102170442752495L;
	
	protected String codUser;
	protected DocenteDTO docenteInfo;

	public String getCodUser() {
		return codUser;
	}

	public void setCodUser(String codUser) {
		this.codUser = codUser;
	}

	public DocenteDTO getDocenteInfo() {
		return docenteInfo;
	}

	public void setDocenteInfo(DocenteDTO docenteInfo) {
		this.docenteInfo = docenteInfo;
	}
	
	
}
