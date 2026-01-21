package com.co.lep.gestion.estudiantes.ws.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.co.lep.gestion.estudiantes.ws.entity.ChatMessage;

public interface ChatMessageRepository extends JpaRepository<ChatMessage, Long> {
    List<ChatMessage> findByRoomIdOrderByTimestampAsc(String roomId);
    
    List<ChatMessage> findBySenderIdAndRecipientIdOrderByTimestampAsc(String sender, String recipient);
    
	List<ChatMessage> findBySenderIdAndRecipientIdOrSenderIdAndRecipientIdOrderByTimestampAsc(String requester,
			String username, String username2, String requester2);
	
	@Query("SELECT COUNT(m) FROM ChatMessage m WHERE m.senderId = :sender AND m.recipientId = :recipient AND m.seen = false")
	Long countUnseenMessages(@Param("sender") String sender, @Param("recipient") String recipient);
	
	@Query("SELECT DISTINCT m.senderId FROM ChatMessage m WHERE m.recipientId = :recipientId")
	public List<String> findDistinctSenders(@Param("recipientId") String recipientId);

	List<ChatMessage> findBySenderIdAndRecipientIdAndSeenFalse(String senderId, String recipientId);

}

