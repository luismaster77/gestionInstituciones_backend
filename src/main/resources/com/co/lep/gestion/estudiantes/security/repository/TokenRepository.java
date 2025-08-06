package com.co.flexicraftsolutions.gestion.estudiantes.security.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.co.flexicraftsolutions.gestion.estudiantes.security.dto.TokenDTO;
import com.co.flexicraftsolutions.gestion.estudiantes.security.entity.Token;

public interface TokenRepository extends JpaRepository<Token, Long> {
	
	Token save(TokenDTO tokenDTO);

	Token findByToken(String token);
	
}
