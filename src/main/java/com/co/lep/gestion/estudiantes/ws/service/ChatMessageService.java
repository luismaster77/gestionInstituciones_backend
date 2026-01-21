package com.co.lep.gestion.estudiantes.ws.service;

import java.time.Instant;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import com.co.lep.gestion.estudiantes.ws.dto.ChatMessagePayload;
import com.co.lep.gestion.estudiantes.ws.dto.HistoryRequest;
import com.co.lep.gestion.estudiantes.ws.entity.ChatMessage;
import com.co.lep.gestion.estudiantes.ws.repository.ChatMessageRepository;

@Service
public class ChatMessageService {

    private final ChatMessageRepository repo;
    private final SimpMessagingTemplate messagingTemplate;

    public ChatMessageService(ChatMessageRepository repo, SimpMessagingTemplate messagingTemplate ) {
        this.repo = repo;
		this.messagingTemplate = messagingTemplate;
    }

    public ChatMessage saveMessageFromPayload(ChatMessagePayload payload, String senderId) {
        ChatMessage m = new ChatMessage();
        m.setSenderId(senderId);
        m.setRecipientId(payload.getRecipientId());
        m.setRoomId(payload.getRoomId());
        m.setContent(payload.getContent());
        m.setTimestamp(Instant.now());
        m.setType(payload.getType() != null ? payload.getType() : "CHAT");
        return repo.save(m);
    }

    public List<ChatMessage> getHistory(HistoryRequest req, String requester) {
        if (req.getRoomId() != null) {
            return repo.findByRoomIdOrderByTimestampAsc(req.getRoomId());
        } else {
            //return repo.findBySenderIdAndRecipientIdOrderByTimestampAsc(requester, req.getOtherUser().getUsername());
        	    return repo.findBySenderIdAndRecipientIdOrSenderIdAndRecipientIdOrderByTimestampAsc(
        	        requester, req.getOtherUser().getUsername(),
        	        req.getOtherUser().getUsername(), requester
        	    );
        }
    }

	public void markMessageAsSeen(Long messageId, String recipientId) {
		ChatMessage msg = repo.findById(messageId).orElseThrow();
	    if (!msg.isSeen() && msg.getRecipientId().equals(recipientId)) {
	        msg.setSeen(true);
	        repo.save(msg);

	        // Notificar al remitente
	        messagingTemplate.convertAndSendToUser(
	            msg.getSenderId(),
	            "/queue/message.seen",
	            msg.getId()
	        );
	    }	
	}
	
	public Map<String, Long> getUnseenCounts(String recipientId) {
	    // Devuelve un mapa: clave=username del remitente, valor=mensajes no leídos
	    Map<String, Long> counts = new HashMap<>();
	    List<String> senders = repo.findDistinctSenders(recipientId); // obtén todos los contactos
	    for (String sender : senders) {
	        counts.put(sender, repo.countUnseenMessages(sender, recipientId));
	    }
	    return counts;
	}

	public void markAllMessagesAsSeen(String senderId, String recipientId) {
	    List<ChatMessage> messages = repo.findBySenderIdAndRecipientIdAndSeenFalse(senderId, recipientId);
	    messages.forEach(m -> m.setSeen(true));
	    repo.saveAll(messages);
	}

}