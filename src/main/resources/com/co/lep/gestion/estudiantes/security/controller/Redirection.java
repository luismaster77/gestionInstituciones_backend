package com.co.lep.gestion.estudiantes.security.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;


@RestController
@RequestMapping("/login1")
public class Redirection {

    private final RestTemplate restTemplate;

    @Value("${jwt.token}")
    private String jwtToken;

    private String servicio = "login";

    public Redirection(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @PostMapping
    public ResponseEntity<String> redireccionar(@RequestBody Object object) {
        // Realizar la redirección al servicio específico en el servidor
        //ResponseEntity<String> responseEntity = restTemplate.getForEntity(jwtToken, String.class);
        ResponseEntity<String> responseEntity = restTemplate.postForEntity(jwtToken, object, String.class);

        return ResponseEntity.ok(responseEntity.getBody());
    }
}

