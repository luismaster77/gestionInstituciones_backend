package com.co.lep.gestion.estudiantes.ws.websocket;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Component;

import com.co.lep.gestion.estudiantes.ws.dto.UserConnectionInfo;

@Component
public class UserSessionRegistry {

    /**
     * username -> set(sessionId)
     */
    private final Map<String, Set<String>> sessions = new ConcurrentHashMap<>();

    /**
     * Agrega una sesión a un usuario.
     */
    public void addSession(String username, String sessionId) {
        sessions.compute(username, (key, set) -> {
            if (set == null) set = Collections.synchronizedSet(new HashSet<>());
            set.add(sessionId);
            return set;
        });
    }

    /**
     * Elimina una sesión de un usuario. Si ya no tiene sesiones, elimina el usuario.
     */
    public void removeSession(String username, String sessionId) {
        sessions.computeIfPresent(username, (key, set) -> {
            set.remove(sessionId);
            if (set.isEmpty()) return null;
            return set;
        });
    }

    /**
     * Devuelve todas las sesiones de un usuario.
     */
    public Set<String> getSessions(String username) {
        return sessions.getOrDefault(username, Collections.emptySet());
    }

    /**
     * Devuelve todos los usuarios conectados.
     */
    public Set<String> getAllUsers() {
        return sessions.keySet();
    }

    /**
     * Devuelve un mapa inmutable de usuarios y sus sesiones (para debug o info)
     */
   
    public Map<String, List<String>> getAll() {
        Map<String, List<String>> copy = new ConcurrentHashMap<>();
        sessions.forEach((user, set) -> copy.put(user, new ArrayList<>(set)));
        return copy;
    }
}