package com.co.lep.gestion.estudiantes.ws.controller;

import java.security.Principal;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.co.lep.gestion.estudiantes.security.jwt.TokenProvider;
import com.co.lep.gestion.estudiantes.ws.dto.ChatMessagePayload;
import com.co.lep.gestion.estudiantes.ws.dto.HistoryRequest;
import com.co.lep.gestion.estudiantes.ws.dto.MessageSeenRequest;
import com.co.lep.gestion.estudiantes.ws.entity.ChatMessage;
import com.co.lep.gestion.estudiantes.ws.service.ChatMessageService;
import com.co.lep.gestion.estudiantes.ws.websocket.UserSessionRegistry;
import com.co.lep.gestion.estudiantes.ws.websocket.WebSocketEventListener;

@RestController
public class ChatController {

	private final SimpMessagingTemplate messagingTemplate;
	private final ChatMessageService messageService;

	@Autowired
	WebSocketEventListener webSocketEventListener;

	@Autowired
	private UserSessionRegistry registry;

	@Autowired
	private TokenProvider tokenProvider;

	public ChatController(SimpMessagingTemplate messagingTemplate, ChatMessageService messageService) {
		this.messagingTemplate = messagingTemplate;
		this.messageService = messageService;
	}

	@MessageMapping("/chat.send")
	public void sendMessage(ChatMessagePayload payload, Principal principal) {
		System.out.println("Mensaje recibido en backend: " + payload);

		if (principal == null || principal.getName() == null) {
			System.out.println("❌ sendMessage: principal es null, rechazando mensaje");
			return;
		}

		// Guardar mensaje
		ChatMessage msg = messageService.saveMessageFromPayload(payload, principal.getName());

		// Debug: sesiones activas
		System.out.println("Sesiones activas: " + registry.getAll());

		if (payload.getRoomId() != null) {
			messagingTemplate.convertAndSend("/topic/room." + payload.getRoomId(), msg);
		} else if (payload.getRecipientId() != null) {
			messagingTemplate.convertAndSendToUser(payload.getRecipientId(), "/queue/messages", msg);
			messagingTemplate.convertAndSendToUser(principal.getName(), "/queue/messages", msg);
		}

		System.out.println("Enviando mensaje a: " + payload.getRecipientId() + " desde: " + principal.getName());
	}

	@MessageMapping("/chat.seen")
	public void markAsSeen(MessageSeenRequest req) {
		// req contiene messageId y recipientId
		messageService.markMessageAsSeen(req.getMessageId(), req.getRecipientId());
	}

	@MessageMapping("/online-users") // app/online-users
	public void sendOnlineUsers(Principal principal, SimpMessageHeaderAccessor headerAccessor) {

		if (principal == null || principal.getName() == null) {
			// No hay principal (no autenticado) -> no hacemos nada
			return;
		}

		Map<String, Object> attrs = headerAccessor.getSessionAttributes();

		if (attrs == null) {
			System.out.println("❌ No hay atributos de sesión WebSocket");
			return;
		}

		String token = (String) attrs.get("token");

		if (token == null) {
			System.out.println("❌ No se encontró token en atributos");
			return;
		}

		// Extraer idInstitucion (lo debiste guardar como claim personalizado)
		Long idInstitucion = tokenProvider.getIdInstitucion(token);
		Boolean isAdmin = tokenProvider.isAdmin(token);

		List<Map<String, String>> users;

		if (Boolean.TRUE.equals(isAdmin)) {
			// Admin → ver TODOS los usuarios online
			users = webSocketEventListener.getOnlineUsersPayload();
		} else {
			// Usuario normal → ver solo los de su institución
			users = webSocketEventListener.getOnlineUsersPayload(idInstitucion);
		}

		messagingTemplate.convertAndSendToUser(principal.getName(), "/queue/online-users", users);
	}

	// subscribirse a /app/chat.history para pedir historico (opcional)
	@MessageMapping("/chat.history")
	@SendToUser("/queue/history")
	public List<ChatMessage> history(HistoryRequest req, Principal principal) {
		if (principal == null || principal.getName() == null) {
			System.out.println("❌ history: principal es null, devolviendo lista vacía");
			return List.of();
		}
		return messageService.getHistory(req, principal.getName());
	}

	@GetMapping("/chat/unseen-counts")
	public Map<String, Long> getUnseenCounts(Principal principal) {
		return messageService.getUnseenCounts(principal.getName());
	}

	@PostMapping("/chat/mark-seen/{senderId}")
	public void markAllAsSeen(@PathVariable String senderId, Principal principal) {
		String currentUser = principal.getName();
		messageService.markAllMessagesAsSeen(senderId, currentUser);
	}
}