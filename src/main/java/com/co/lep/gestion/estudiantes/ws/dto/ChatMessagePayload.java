package com.co.lep.gestion.estudiantes.ws.dto;

import lombok.Data;

@Data
public class ChatMessagePayload {
    private String content;
    private String recipientId;
    private String roomId;
    private String type; // CHAT, JOIN, LEAVE
}
