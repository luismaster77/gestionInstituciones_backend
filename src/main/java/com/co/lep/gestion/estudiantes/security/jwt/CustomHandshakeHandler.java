package com.co.lep.gestion.estudiantes.security.jwt;

import java.security.Principal;
import java.util.Map;
import org.springframework.http.server.ServerHttpRequest;

public class CustomHandshakeHandler {

    protected Principal determineUser(ServerHttpRequest request, Map<String, Object> attributes) {

        return (Principal) attributes.get("user");
    }
}
