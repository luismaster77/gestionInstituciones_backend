package com.co.lep.gestion.estudiantes.security.jwt;

import java.security.Key;
import java.security.SignatureException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.co.lep.gestion.estudiantes.constantes.Constantes;
import com.co.lep.gestion.estudiantes.exepciones.RegistroNoEncontradoException;
import com.co.lep.gestion.estudiantes.repository.UserRepository;
import com.co.lep.gestion.estudiantes.security.dto.TokenDTO;
import com.co.lep.gestion.estudiantes.security.entity.Token;
import com.co.lep.gestion.estudiantes.security.entity.User;
import com.co.lep.gestion.estudiantes.security.mapper.TokenMapper;
import com.co.lep.gestion.estudiantes.security.repository.TokenRepository;
import com.co.lep.gestion.estudiantes.utilidades.Validador;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class TokenProvider {

	Key key = Keys.secretKeyFor(SignatureAlgorithm.HS512);

	@Autowired
	TokenRepository tokenRepository;

	@Autowired
	UserRepository userRepository;

	@SuppressWarnings("deprecation")
	public String createToken(Authentication authentication) {
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		Date now = new Date();
		Date expiryDate = new Date(now.getTime() + TimeUnit.HOURS.toMillis(5));

		User userEntity = userRepository.findByUsername(userDetails.getUsername())
				.orElseThrow(() -> new RegistroNoEncontradoException("Usuario no encontrado"));

		// Si NO tiene institución → admin o superusuario
		Long idInstitucion = null;
		if (userEntity.getInstitucionId() != null) {
			idInstitucion = userEntity.getInstitucionId().getId();
		}

		// Crear claims adicionales
		Map<String, Object> claims = new HashMap<>();
		
		if (idInstitucion != null) {
	        claims.put("idInstitucion", idInstitucion);
	    }

		claims.put("isAdmin", idInstitucion == null);

		String token = Jwts.builder().setClaims(claims).setSubject(userDetails.getUsername()).setIssuedAt(new Date())
				.setExpiration(expiryDate).signWith(SignatureAlgorithm.HS512, key).compact();

		TokenDTO tokenDTO = new TokenDTO();
		tokenDTO.setToken(token);
		tokenDTO.setExpiradatetoken(expiryDate);
		tokenDTO.setUsuario(userEntity);
		tokenDTO.setFecAcceso(now);

		Token tokenEntity = TokenMapper.INSTANCE.toEntity(tokenDTO);

		tokenRepository.save(tokenEntity);

		return token;
	}

	public String resolveToken(HttpServletRequest request) {
		String bearerToken = request.getHeader(Constantes.HEADER_AUTHORIZATION_KEY);
		if (StringUtils.hasText(bearerToken) && bearerToken.startsWith(Constantes.TOKEN_BEARER_PREFIX)) {
			return bearerToken.substring(7);
		}
		return null;
	}

	@SuppressWarnings("deprecation")
	public boolean validateToken(String token) throws SignatureException {
		try {
			if (Validador.esNuloOVacio(token)) {
				return false;
			} else {
				Jwts.parser().setSigningKey(key).parseClaimsJws(token);
			}
			return true;
		} catch (MalformedJwtException ex) {
			log.error("Invalid JWT token");
		} catch (ExpiredJwtException ex) {
			log.error("Expired JWT token");
		} catch (UnsupportedJwtException ex) {
			log.error("Unsupported JWT token");
		} catch (IllegalArgumentException ex) {
			log.error("JWT claims string is empty");
		} catch (JwtException e) {
			log.error("Error en el token");
		}
		return false;
	}

	public boolean validateTokenChat(String token) {
		try {
			if (token == null || token.isEmpty()) {
				return false;
			}

			Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);

			return true;

		} catch (ExpiredJwtException ex) {
			log.error("Token expirado");
		} catch (JwtException ex) {
			log.error("Token inválido");
		}
		return false;
	}

	@SuppressWarnings("deprecation")
	public String getUsername(String token) {
		return Jwts.parser().setSigningKey(key).parseClaimsJws(token).getBody().getSubject();
	}

	public Claims extractAllClaims(String token) {
		return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody();
	}

	public Long getIdInstitucion(String token) {
	    Claims claims = extractAllClaims(token);
	    Object value = claims.get("idInstitucion");

	    if (value == null) return null;

	    if (value instanceof Integer) {
	        return ((Integer) value).longValue();
	    }

	    if (value instanceof Long) {
	        return (Long) value;
	    }

	    return Long.valueOf(value.toString());
	}

	public Boolean isAdmin(String token) {
	    Claims claims = extractAllClaims(token);
	    Object value = claims.get("isAdmin");

	    if (value == null) return false;

	    if (value instanceof Boolean) {
	        return (Boolean) value;
	    }

	    return Boolean.parseBoolean(value.toString());
	}
}
