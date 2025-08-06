package com.co.lep.gestion.estudiantes.security.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.co.lep.gestion.estudiantes.security.dto.TokenDTO;
import com.co.lep.gestion.estudiantes.security.entity.Token;

public interface TokenRepository extends JpaRepository<Token, Long> {
	
	Token save(TokenDTO tokenDTO);

	Token findByToken(String token);
}
