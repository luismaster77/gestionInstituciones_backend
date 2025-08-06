package com.co.lep.gestion.estudiantes.security.dto;

import com.co.lep.gestion.estudiantes.dto.DocenteDTO;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public class AuthenticationResponseWithUser {
    private String jwt;
    private boolean setCambioPassword;
    private DocenteDTO docenteInfo;

    public AuthenticationResponseWithUser(String jwt, boolean setCambioPassword, DocenteDTO docenteInfo) {
        this.jwt = jwt;
        this.setCambioPassword = setCambioPassword;
        this.docenteInfo = docenteInfo;
    }

    // Getters y setters
    public String getJwt() {
        return jwt;
    }

    public void setJwt(String jwt) {
        this.jwt = jwt;
    }

    public boolean isSetCambioPassword() {
        return setCambioPassword;
    }

    public void setSetCambioPassword(boolean setCambioPassword) {
        this.setCambioPassword = setCambioPassword;
    }

    public DocenteDTO getDocenteInfo() {
        return docenteInfo;
    }

    public void setDocenteInfo(DocenteDTO docenteInfo) {
        this.docenteInfo = docenteInfo;
    }
}

