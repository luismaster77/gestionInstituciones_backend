package com.co.lep.gestion.estudiantes.ws.dto;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import lombok.Data;

@Data
public class UserConnectionInfo {
    private String username;
    private Long idInstitucion;
    private boolean isAdmin;
    private Set<String> sessions = Collections.synchronizedSet(new HashSet<>());
}

