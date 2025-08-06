package com.co.lep.gestion.estudiantes.security.jwt;

import java.io.IOException;
import java.security.SignatureException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.co.lep.gestion.estudiantes.utilidades.Validador;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtTokenFilter extends OncePerRequestFilter {

   @Autowired
   private TokenProvider jwtProvider;

   @Autowired
   private UserDetailsService userDetailsService;

   @Override
   protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
	   try {
		    String token = jwtProvider.resolveToken(httpServletRequest);
		  
		    if (!Validador.cadenaEstaVaciaONula(token) && jwtProvider.validateToken(token)) {  
		      UserDetails userDetails = userDetailsService.loadUserByUsername(
		    		  jwtProvider.getUsername(token));
		      UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
		          userDetails, null, userDetails.getAuthorities());
		      authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(httpServletRequest));
		      SecurityContextHolder.getContext().setAuthentication(authentication);
		    }

		    filterChain.doFilter(httpServletRequest, httpServletResponse);
	    } catch (AuthenticationException e) {
	        httpServletResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED, e.getMessage());
	    } catch (SignatureException e) {
			e.printStackTrace();
		}
   }
}
