package com.co.lep.gestion.estudiantes.ws.dto;

import lombok.Data;

@Data
public class HistoryRequest {

    // Si se envía roomId → historial del canal/grupo
    private String roomId;

    // Si se envía otherUser → historial 1 a 1 con ese usuario
    private UserDTO otherUser;

    // Paginación opcional (por si quieres más adelante)
    private Integer limit = 50;
}




