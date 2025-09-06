package com.co.lep.gestion.estudiantes.auth.controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.security.auth.login.AccountLockedException;
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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.auth0.jwt.exceptions.TokenExpiredException;
import com.co.lep.gestion.estudiantes.constantes.Constantes;
import com.co.lep.gestion.estudiantes.constantes.Mensajes;
import com.co.lep.gestion.estudiantes.entity.EntityResponse;
import com.co.lep.gestion.estudiantes.exepciones.MaxCountExceededException;
import com.co.lep.gestion.estudiantes.exepciones.PasswordResetException;
import com.co.lep.gestion.estudiantes.exepciones.RegistroNoEncontradoException;
import com.co.lep.gestion.estudiantes.exepciones.RegistroNoGuardadoException;
import com.co.lep.gestion.estudiantes.exepciones.TokenNotFoundException;
import com.co.lep.gestion.estudiantes.repository.UserRepository;
import com.co.lep.gestion.estudiantes.security.dto.AuthenticationRequest;
import com.co.lep.gestion.estudiantes.security.dto.AuthenticationResponse;
import com.co.lep.gestion.estudiantes.security.dto.AuthenticationResponseWithUser;
import com.co.lep.gestion.estudiantes.security.dto.UsuarioDTO;
import com.co.lep.gestion.estudiantes.security.entity.User;
import com.co.lep.gestion.estudiantes.security.jwt.TokenProvider;
import com.co.lep.gestion.estudiantes.security.service.TokenService;
import com.co.lep.gestion.estudiantes.service.IUsuarioService;
import com.co.lep.gestion.estudiantes.service.impl.EmailService;
import com.co.lep.gestion.estudiantes.utilidades.LoginGenerator;
import com.co.lep.gestion.estudiantes.utilidades.UserLoginApp;
import com.co.lep.gestion.estudiantes.utilidades.Validador;

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
	private UserRepository userRepository;
	
	@Autowired
	EmailService emailService;
	
	@Autowired
	IUsuarioService iUsuarioService;

	@PostMapping(value = Constantes.REQUEST_TOKEN_URL)
	public ResponseEntity<?> authenticateUser(@RequestBody AuthenticationRequest authenticationRequest) {
		try {
			Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
					authenticationRequest.getUsername(), authenticationRequest.getPassword()));
			SecurityContextHolder.getContext().setAuthentication(authentication);
			String jwt = jwtTokenProvider.createToken(authentication);

			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			
			User userInfo = userRepository.findByUsername(auth.getName())
					.orElseThrow(() -> new RegistroNoEncontradoException("Usuario no encontrado"));
			
			if (userInfo.getEstadoId().getCodValor().equals("B") || userInfo.getEstadoId().getCodValor().equals("I")) {
	            throw new AccountLockedException("El usuario está bloqueado.");
	        }

			userLoginApp.setUsuarioInfoDTO(userInfo);

			User user = (User) authentication.getPrincipal();
			boolean setCambioPassword = user.getCambioPassword();
			AuthenticationResponseWithUser response = new AuthenticationResponseWithUser(jwt, setCambioPassword,
					userInfo);
			return ResponseEntity.ok(response);

		} catch (BadCredentialsException ex) {
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
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
		} catch (AccountLockedException ex) {
	        // Usuario bloqueado
	        EntityResponse<?> errorResponse = new EntityResponse<>();
	        errorResponse.setSuccess(false);
	        errorResponse.setMessage("Su cuenta está bloqueada. Por favor, contacte al administrador.");
	        return ResponseEntity.status(HttpStatus.LOCKED).body(errorResponse);

	    } catch (Exception e) {
			// Otros errores internos del servidor
			EntityResponse<?> errorResponse = new EntityResponse<>();
			errorResponse.setSuccess(false);
			errorResponse.setMessage(Mensajes.TXT_ERROR_APLICACION);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
		}
	}

	@PostMapping(value = Constantes.REQUEST_REFRESH_TOKEN)
	public ResponseEntity<?> refreshAuthToken(HttpServletRequest request) {
		try {

			String authToken = jwtTokenProvider.resolveToken(request);
			String newToken = "";

			if (!Validador.esNuloOVacio(authToken)) {
				String token = tokenService.validarToken(authToken);

				if (Validador.esNuloOVacio(token)) {
					newToken = authToken;
				} else {
					newToken = token;
				}
			}

			return ResponseEntity.ok(new AuthenticationResponse(newToken, true));

		} catch (UsernameNotFoundException ex) {
			// Registro no encontrado
			EntityResponse<?> errorResponse = new EntityResponse<>();
			errorResponse.setSuccess(false);
			errorResponse.setMessage(Mensajes.TXT_USUARIO_NOT_FOUND);
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
		} catch (Exception e) {
			// Otros errores internos del servidor
			EntityResponse<?> errorResponse = new EntityResponse<>();
			errorResponse.setSuccess(false);
			errorResponse.setMessage(Mensajes.TXT_ERROR_APLICACION);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
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
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
		}
	}

	@PostMapping(value = Constantes.ENDPOINT_GENERA_LOGIN)
	public ResponseEntity<EntityResponse<List<String>>> consultarDocente(@Valid @RequestBody UsuarioDTO usuarioDTO) {
		try {

			List<User> codDocentes = userRepository.findAll();

			List<String> usernames = codDocentes.stream().map(User::getUsername)
					.collect(Collectors.toList());

			List<String> docentesList = LoginGenerator.generarLogins(usuarioDTO, usernames);

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
	
	@PostMapping(value = Constantes.PASSWORD_RESET)
	public ResponseEntity<?> solicitarRestablecimiento(@RequestBody String email) {
		try {
			
			iUsuarioService.generarResetPwdToken(email);
			EntityResponse<?> response = new EntityResponse<>();
			response.setSuccess(true);
			response.setData(null);
			response.setMessage("Si el correo electrónico existe, se enviará un enlace para restablecer la contraseña, por favor revise su bandeja de entrada");
			
			return ResponseEntity.ok(response);
			
		} catch (RegistroNoEncontradoException ex) {
			EntityResponse<?> errorResponse = new EntityResponse<>();
			errorResponse.setSuccess(false);
			errorResponse.setMessage(ex.getMessage());
			return ResponseEntity.ok(errorResponse);
		} catch (RegistroNoGuardadoException ex) {
			EntityResponse<?> errorResponse = new EntityResponse<>();
			errorResponse.setSuccess(false);
			errorResponse.setMessage(ex.getMessage());
			return ResponseEntity.ok(errorResponse);
		} catch (Exception e) {
			EntityResponse<?> errorResponse = new EntityResponse<>();
			errorResponse.setSuccess(false);
			errorResponse.setMessage(Mensajes.TXT_ERROR_APLICACION + e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
		}
	}
	
	@GetMapping(value = Constantes.VALIDA_PASSWORD_RESET)
	public ResponseEntity<?> validarTokenResetPassword(@RequestParam("token") String token) {
		EntityResponse<?> response = new EntityResponse<>();
		try {
	        // Llamar al servicio que valida el token
	        iUsuarioService.validateResetToken(token);
	        response.setSuccess(true);
	        response.setMessage("Token válido");
	        return ResponseEntity.ok(response);
	        
	    } catch (TokenExpiredException ex) {
	        // Manejar token expirado
	        response.setSuccess(false);
	        response.setMessage("El token ha expirado");
	        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);

	    } catch (TokenNotFoundException e) {
	    	response.setSuccess(false);
	        response.setMessage("Token no válido");
	        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
	  
	    }  catch (Exception e) {
	        // Manejar cualquier otro error no esperado
	        response.setSuccess(false);
	        response.setMessage(Mensajes.TXT_ERROR_APLICACION + e.getMessage());
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
	    }
	}

	
	@GetMapping(value = Constantes.RESET_PASSWORD)
	public ResponseEntity<EntityResponse<?>> showResetPasswordForm(@RequestParam("token") String token, @RequestParam("newPassword") String newPassword) {
	    EntityResponse<?> response = new EntityResponse<>();

	    try {
	        // Llamada al servicio que valida el token
	        iUsuarioService.resetPassword(token, newPassword);
	        
	        // Si el token es válido, devolver respuesta de éxito
	        response.setSuccess(true);
	        response.setMessage("Cambio de contraseña exitoso");
	        return ResponseEntity.ok(response);

	    } catch (TokenExpiredException ex) {
	        // Manejar token expirado
	        response.setSuccess(false);
	        response.setMessage("El token ha expirado");
	        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);

	    } catch (TokenNotFoundException ex) {
	        // Manejar token no encontrado
	        response.setSuccess(false);
	        response.setMessage("Token no encontrado");
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);

	    } catch (MaxCountExceededException ex) {
	        // Manejar intentos excedidos
	        response.setSuccess(false);
	        response.setMessage("Se excedieron los intentos de reseteo de contraseña");
	        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(response);

	    } catch (PasswordResetException ex) {
	        // Manejar otros errores de reseteo de contraseña
	        response.setSuccess(false);
	        response.setMessage("Error en el proceso de reseteo de contraseña: " + ex.getMessage());
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);

	    } catch (Exception e) {
	        // Manejar cualquier otro error no esperado
	        response.setSuccess(false);
	        response.setMessage(Mensajes.TXT_ERROR_APLICACION + e.getMessage());
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
	    }
	}
}