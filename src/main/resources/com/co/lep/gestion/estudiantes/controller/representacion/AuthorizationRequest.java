package com.co.lep.gestion.estudiantes.controller.representacion;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;

@Builder
@AllArgsConstructor
public class AuthorizationRequest implements Serializable {

	private static final long serialVersionUID = 1L;

	@JsonProperty("user")
	private String username;

	private String password;

	public AuthorizationRequest() {
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
