package com.co.lep.gestion.estudiantes.ws.entity;

import java.time.Instant;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "chat_message")
@Data
public class ChatMessage {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // quien envía (puede ser id numérico o username según tu modelo)
    private String senderId;

    // receptor (null si es mensaje de canal/grupal)
    private String recipientId;

    // id del canal / sala (por ejemplo "course-5" o null para mensajes 1:1 con recipientId)
    private String roomId;

    @Column(columnDefinition = "TEXT")
    private String content;

    private Instant timestamp;

    private boolean readFlag = false;

    private String type;
    
    @Column(nullable = false)
    private boolean seen = false;
}
