package com.co.lep.gestion.estudiantes.entity;

import java.io.Serializable;

public class EntityResponse<T> implements Serializable{

	private static final long serialVersionUID = 8258662708702719315L;
	
	private boolean success;
    private String message;
    private T data;
    
	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public T getData() {
		return data;
	}
	public void setData(T data) {
		this.data = data;
	}
}
