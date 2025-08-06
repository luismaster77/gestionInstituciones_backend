package com.co.flexicraftsolutions.gestion.estudiantes.auth.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.co.flexicraftsolutions.gestion.estudiantes.constantes.Constantes;
import com.co.flexicraftsolutions.gestion.estudiantes.constantes.Mensajes;
import com.co.flexicraftsolutions.gestion.estudiantes.dto.DocenteDTO;
import com.co.flexicraftsolutions.gestion.estudiantes.entity.EntityResponse;
import com.co.flexicraftsolutions.gestion.estudiantes.exepciones.RegistroNoEncontradoException;
import com.co.flexicraftsolutions.gestion.estudiantes.impl.service.AdminService;
import com.co.flexicraftsolutions.gestion.estudiantes.repository.DocenteRepository;
import com.co.flexicraftsolutions.gestion.estudiantes.security.dto.AuthenticationRequest;
import com.co.flexicraftsolutions.gestion.estudiantes.security.dto.AuthenticationResponse;
import com.co.flexicraftsolutions.gestion.estudiantes.security.dto.AuthenticationResponseWithUser;
import com.co.flexicraftsolutions.gestion.estudiantes.security.entity.User;
import com.co.flexicraftsolutions.gestion.estudiantes.security.jwt.TokenProvider;
import com.co.flexicraftsolutions.gestion.estudiantes.security.service.TokenService;
import com.co.flexicraftsolutions.gestion.estudiantes.utilidades.LoginGenerator;
import com.co.flexicraftsolutions.gestion.estudiantes.utilidades.UserLoginApp;
import com.co.flexicraftsolutions.gestion.estudiantes.utilidades.Validador;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
@RestController
@CrossOrigin
@RequestMapping(Constantes.REQUEST_URL_AUTH)
public class LoginController {

    @Autowired
    private TokenProvider jwtTokenProvider;
    
    @Autowired
    private AuthenticationManager authenticationManager;
    
    @Autowired
    private TokenService tokenService;
    
    @Autowired
    private UserLoginApp userLoginApp;
    
    @Autowired
    private AdminService adminService;
    
    @Autowired
    private DocenteRepository docenteRepository;

	@PostMapping(value = Constantes.REQUEST_TOKEN_URL)
	public ResponseEntity<?> authenticateUser(@RequestBody AuthenticationRequest authenticationRequest) {
		try {
			Authentication authentication = authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(
							authenticationRequest.getUsername(), 
							authenticationRequest.getPassword()
							)
					);
			SecurityContextHolder.getContext().setAuthentication(authentication);
			String jwt = jwtTokenProvider.createToken(authentication);
			
			Authentication auth = SecurityContextHolder.getContext().getAuthentication(); 
			
			userLoginApp.setCodUser(auth.getName());
			DocenteDTO docenteInfoDTO = adminService.obtenerDocentePorCodigo(auth.getName());
			userLoginApp.setDocenteInfo(docenteInfoDTO);
			
			User user = (User) authentication.getPrincipal();
		    boolean setCambioPassword = user.isSetCambioPassword();
		    AuthenticationResponseWithUser response = new AuthenticationResponseWithUser(jwt, setCambioPassword, docenteInfoDTO);
			return ResponseEntity.ok(response);
			
		}catch (BadCredentialsException ex) {
	        // Credenciales incorrectas (usuario o contraseña)
			EntityResponse<?> errorResponse = new EntityResponse<>();
            errorResponse.setSuccess(false);
            errorResponse.setMessage(Mensajes.TXT_BAD_CREDENCIALES);
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorResponse);
	    } catch (UsernameNotFoundException ex) {
	        // Registro no encontrado
	    	EntityResponse<?> errorResponse = new EntityResponse<>();
            errorResponse.setSuccess(false);
            errorResponse.setMessage(Mensajes.TXT_USUARIO_NOT_FOUND);
	        return ResponseEntity.status(HttpStatus.NOT_FOUND)
	                             .body(errorResponse);
	    } catch (Exception e) {
	        // Otros errores internos del servidor
	    	EntityResponse<?> errorResponse = new EntityResponse<>();
            errorResponse.setSuccess(false);
            errorResponse.setMessage(Mensajes.TXT_ERROR_APLICACION);
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
	                             .body(errorResponse);
	    }
	}
	
	@PostMapping(value = Constantes.REQUEST_REFRESH_TOKEN)
    public ResponseEntity<?> refreshAuthToken(HttpServletRequest request) {
		try {
			
			String authToken = jwtTokenProvider.resolveToken(request);
			String newToken = "";
			
			if(!Validador.cadenaEstaVaciaONula(authToken)) {
				String token = tokenService.validarToken(authToken);
				
				if (Validador.cadenaEstaVaciaONula(token)) {
					newToken = authToken;
				}else {
					newToken = token;
				}
			}
			
			return ResponseEntity.ok(new AuthenticationResponse(newToken, true));
			
		} catch (UsernameNotFoundException ex) {
	        // Registro no encontrado
	    	EntityResponse<?> errorResponse = new EntityResponse<>();
            errorResponse.setSuccess(false);
            errorResponse.setMessage(Mensajes.TXT_USUARIO_NOT_FOUND);
	        return ResponseEntity.status(HttpStatus.NOT_FOUND)
	                             .body(errorResponse);
	    } catch (Exception e) {
			 // Otros errores internos del servidor
	    	EntityResponse<?> errorResponse = new EntityResponse<>();
            errorResponse.setSuccess(false);
            errorResponse.setMessage(Mensajes.TXT_ERROR_APLICACION);
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
	                             .body(errorResponse);
		}
    }
	
	@PostMapping(value = Constantes.REQUEST_LOGOUT)
	public ResponseEntity<?> logout(HttpServletRequest request, HttpServletResponse response) {
	 try {
		 new SecurityContextLogoutHandler().logout(request, response,
				 SecurityContextHolder.getContext().getAuthentication());
		 return ResponseEntity.ok(HttpStatus.OK);
	} catch (Exception e) {
		// Otros errores internos del servidor
    	EntityResponse<?> errorResponse = new EntityResponse<>();
        errorResponse.setSuccess(false);
        errorResponse.setMessage(Mensajes.TXT_ERROR_APLICACION);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                             .body(errorResponse);
		}       
	}
	
	@PostMapping(value = Constantes.ENDPOINT_GENERA_LOGIN)
	public ResponseEntity<EntityResponse<List<String>>> consultarDocente(@Valid @RequestBody DocenteDTO docenteDTO) {
        try {
        	
        	List<String> codDocentes = docenteRepository.findAllCodDocentes();
        	
        	List<String> docentesList = LoginGenerator.generarLogins(docenteDTO,codDocentes);
           
            EntityResponse<List<String>> response = new EntityResponse<>();
            response.setSuccess(true);
            response.setData(docentesList);
            return ResponseEntity.ok(response);
            
        } catch (RegistroNoEncontradoException ex) {
        	EntityResponse<List<String>> errorResponse = new EntityResponse<>();
            errorResponse.setSuccess(false);
            errorResponse.setMessage(ex.getMessage());
            return ResponseEntity.ok(errorResponse);
        } catch (Exception e) {
        	EntityResponse<List<String>> errorResponse = new EntityResponse<>();
            errorResponse.setSuccess(false);
            errorResponse.setMessage(Mensajes.TXT_ERROR_APLICACION + e.getMessage());
        	return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
		}
    }
	
}