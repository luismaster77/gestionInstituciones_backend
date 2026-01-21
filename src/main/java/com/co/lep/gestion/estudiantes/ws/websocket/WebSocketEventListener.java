package com.co.lep.gestion.estudiantes.ws.websocket;

import java.security.Principal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionConnectEvent;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

import com.co.lep.gestion.estudiantes.constantes.Constantes;
import com.co.lep.gestion.estudiantes.repository.UserRepository;
import com.co.lep.gestion.estudiantes.security.entity.User;

import okhttp3.internal.platform.ConscryptPlatform;

@Component
public class WebSocketEventListener {

    private final SimpMessagingTemplate messagingTemplate;

    @Autowired
    private UserSessionRegistry registry; // Registry centralizado
    
    @Autowired
    private UserRepository userRepository;

    public WebSocketEventListener(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }
    
    public List<Map<String, String>> getOnlineUsersPayload() {
        List<Map<String, String>> payload = new ArrayList<>();
        for (String username : registry.getAll().keySet()) {
            Map<String, String> user = new HashMap<>();
            user.put("username", username);

            String fullName = userRepository.findByUsername(username)
                    .map(u -> u.getPrimerNombre() + " " + u.getPrimerApellido())
                    .orElse(username);

            user.put("name", fullName);
            payload.add(user);
        }
        return payload;
    }

    /** Genera payload de usuarios online */
    public List<Map<String, String>> getOnlineUsersPayload(Long idInstitucion) {
        List<Map<String, String>> payload = new ArrayList<>();
        
        for (String username : registry.getAll().keySet()) {
        	
        	Optional<User> opt = userRepository.findByUsername(username);
            if (opt.isEmpty()) continue;
        	
            User u = opt.get();
            
            // ============================================
            // 1) SI ES ADMIN (institución NULL) → SIEMPRE MOSTRAR
            // ============================================
            if (u.getInstitucionId() == null) {
                payload.add(buildUserPayload(u));
                continue;
            }
               
            // ============================================
            // 2) SI ES USUARIO NORMAL → filtrar por institución
            // ============================================
            Long userInstitutionId = u.getInstitucionId().getId();

            if (!userInstitutionId.equals(idInstitucion)) {
                continue; // no coincide -> no agregar
            }

            payload.add(buildUserPayload(u));
            
        }
        return payload;
    }

    /** Evento de conexión */
    @EventListener
    public void handleConnect(SessionConnectEvent event) {
        StompHeaderAccessor accessor = StompHeaderAccessor.wrap(event.getMessage());
        Principal principal = accessor.getUser();
        String sessionId = accessor.getSessionId();
        String token = accessor.getFirstNativeHeader("Authorization");
        
        if (token != null) {
            token = token.replace("Bearer ", "");
        }

        if (principal != null) {
            String username = principal.getName();
            registry.addSession(username, sessionId);

            System.out.println("Usuario conectado: " + username + " (sessionId=" + sessionId + ")");
            System.out.println("Sesiones por usuario: " + registry.getAll());

            // Actualizar lista de usuarios online a todos
            messagingTemplate.convertAndSend("/topic/online-users", getOnlineUsersPayload());
        } else {
            System.out.println("❌ No se pudo obtener usuario en CONNECT. sessionId=" + sessionId);
        }
    }

    /** Evento de desconexión */
    @EventListener
    public void handleDisconnect(SessionDisconnectEvent event) {
        StompHeaderAccessor accessor = StompHeaderAccessor.wrap(event.getMessage());
        String sessionId = accessor.getSessionId();
        Principal principal = accessor.getUser();

        if (principal != null) {
            String username = principal.getName();
            registry.removeSession(username, sessionId);

            System.out.println("Usuario desconectado: " + username + " (sessionId=" + sessionId + ")");
            System.out.println("Sesiones por usuario: " + registry.getAll());

            // Actualizar lista de usuarios online a todos
            messagingTemplate.convertAndSend("/topic/online-users", getOnlineUsersPayload());
        } else {
            System.out.println("⚠️ No se pudo determinar username en disconnect. sessionId=" + sessionId);
        }
    }

    /** Enviar mensaje privado a todas las sesiones de un usuario */
    public void sendPrivateMessage(String toUser, Object payload) {
        Map<String, List<String>> sessions = registry.getAll();
        List<String> sessionIds = sessions.get(toUser);
        if (sessionIds != null) {
            for (String sessionId : sessionIds) {
                messagingTemplate.convertAndSendToUser(sessionId, "/queue/messages", payload);
            }
        }
    }
    
    private Map<String, String> buildUserPayload(User u) {
    	
    	String fullName = u.getPrimerNombre() + " " + u.getPrimerApellido();
    	
    	if(Constantes.ROLE_ADMIN.equals(u.getRoleId().getName())){
    		fullName = fullName +" - " + u.getRoleId().getName();
    	}
    	
        Map<String, String> user = new HashMap<>();
        user.put("username", u.getUsername());
        user.put("name", fullName);
        user.put("id", u.getId().toString());
        return user;
    }
}