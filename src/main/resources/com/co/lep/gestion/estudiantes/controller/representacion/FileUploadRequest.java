package com.co.flexicraftsolutions.gestion.estudiantes.controller.representacion;

import java.io.Serializable;

public class FileUploadRequest implements Serializable{

	private static final long serialVersionUID = 2924414700137183683L;
	
	private String fileName;
    private String fileContent;
    private String referencia;
    
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getFileContent() {
		return fileContent;
	}
	public void setFileContent(String fileContent) {
		this.fileContent = fileContent;
	}
	public String getReferencia() {
		return referencia;
	}
	public void setReferencia(String referencia) {
		this.referencia = referencia;
	}
}
