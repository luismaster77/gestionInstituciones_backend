package com.co.lep.gestion.estudiantes.security.jwt;

import java.security.Principal;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.HandshakeInterceptor;


@Component
public class JwtHandshakeInterceptor implements HandshakeInterceptor {

    @Autowired
    private TokenProvider tokenProvider;

    public static class StompPrincipal implements Principal {
        private final String name;
        public StompPrincipal(String name) { this.name = name; }
        @Override public String getName() { return name; }
    }

    @Override
    public boolean beforeHandshake(ServerHttpRequest request,
                                   ServerHttpResponse response,
                                   WebSocketHandler wsHandler,
                                   Map<String, Object> attributes) throws Exception {

        String token = null;

        // ============================================
        // 1) Obtener token desde ?access_token=
        // ============================================
        if (request instanceof ServletServerHttpRequest servletReq) {

            String queryToken = servletReq.getServletRequest().getParameter("access_token");

            if (queryToken != null && !queryToken.isBlank()) {
                token = queryToken;
            }

            System.out.println("WS HANDSHAKE - query access_token: " + queryToken);
        }

        // ============================================
        // 2) Si no llegó token => rechazar handshake
        // ============================================
        if (token == null) {
            System.out.println("❌ WS HANDSHAKE falló — no llegó access_token");
            return false;
        }

        // ============================================
        // 3) Validar token
        // ============================================
        if (!tokenProvider.validateTokenChat(token)) {
            System.out.println("❌ WS HANDSHAKE falló — token inválido");
            return false;
        }

        // ============================================
        // 4) Extraer username del JWT
        // ============================================
        String username = tokenProvider.getUsername(token);
        Long institutionId = tokenProvider.getIdInstitucion(token);

        System.out.println("🔥 WS HANDSHAKE — Usuario autenticado: " + username);

        // ============================================
        // 5) Guardar Principal REAL en atributos
        // ============================================
        StompPrincipal principal = new StompPrincipal(username);

        // Lo único que Spring realmente necesita
        attributes.put(Principal.class.getName(), principal);

        // (Opcional) guardar username simple
        attributes.put("username", username);
        attributes.put("institucionId",institutionId);
        attributes.put("token", token);

        return true;
    }

    @Override
    public void afterHandshake(ServerHttpRequest request,
                               ServerHttpResponse response,
                               WebSocketHandler wsHandler,
                               Exception exception) {
    }
}

