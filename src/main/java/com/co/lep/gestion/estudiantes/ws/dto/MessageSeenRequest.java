package com.co.lep.gestion.estudiantes.ws.dto;

import lombok.Data;

@Data
public class MessageSeenRequest {
	 private Long messageId;
	 private String recipientId; // opcional
}
