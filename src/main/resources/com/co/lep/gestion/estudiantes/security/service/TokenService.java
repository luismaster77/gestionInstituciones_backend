package com.co.lep.gestion.estudiantes.security.service;

import java.security.SignatureException;
import java.util.Date;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.co.lep.gestion.estudiantes.security.dto.TokenDTO;
import com.co.lep.gestion.estudiantes.security.entity.Token;
import com.co.lep.gestion.estudiantes.security.jwt.TokenProvider;
import com.co.lep.gestion.estudiantes.security.mapper.TokenMapper;
import com.co.lep.gestion.estudiantes.security.repository.TokenRepository;
import com.co.lep.gestion.estudiantes.utilidades.Validador;

import io.jsonwebtoken.Claims;

@Service
public class TokenService {

	@Autowired
    TokenRepository tokenRepository;
	
   @Autowired
    private UserDetailsService userDetailsService;
   
   @Autowired
   private TokenProvider jwtTokenProvider;

	public void update(Token tokenEntity) {
		tokenRepository.save(tokenEntity);
	}

	public String validarToken(String authToken) throws UsernameNotFoundException, SignatureException {
	
		TokenDTO tokenDTO = new TokenDTO();
		tokenDTO.setToken(authToken);
		
		Token tokenEntity = TokenMapper.INSTANCE.toEntity(tokenDTO);

		Token tokenEncontrado = tokenRepository.findByToken(tokenEntity.getToken());
		String newToken = "";
		if(!Validador.cadenaEstaVaciaONula(tokenEncontrado.getToken())) {
			
			String username = jwtTokenProvider.getUsername(authToken);
			UserDetails userDetails = userDetailsService.loadUserByUsername(username);
			UsernamePasswordAuthenticationToken authenticationToken =
					new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
			SecurityContextHolder.getContext().setAuthentication(authenticationToken);
			
			newToken = jwtTokenProvider.createToken(authenticationToken);
			
		}
		return newToken;
	}
	
	public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }
	
	 public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
	        final Claims claims = jwtTokenProvider.extractAllClaims(token);
	        return claimsResolver.apply(claims);
	}
	 
}
