package com.co.lep.gestion.estudiantes.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.co.lep.gestion.estudiantes.constantes.Constantes;
import com.co.lep.gestion.estudiantes.constantes.Mensajes;
import com.co.lep.gestion.estudiantes.dto.PasswordDTO;
import com.co.lep.gestion.estudiantes.entity.EntityResponse;
import com.co.lep.gestion.estudiantes.exepciones.PasswordMismatchException;
import com.co.lep.gestion.estudiantes.exepciones.RegistroNoEncontradoException;
import com.co.lep.gestion.estudiantes.exepciones.RegistroNoGuardadoException;
import com.co.lep.gestion.estudiantes.security.dto.UsuarioDTO;
import com.co.lep.gestion.estudiantes.security.entity.User;
import com.co.lep.gestion.estudiantes.service.IAdminService;

@RestController
@RequestMapping(Constantes.URL_BASE)
public class AdminUsuariosController {

	@Autowired
	IAdminService iAdminService;

	@PostMapping(value = Constantes.ENDPOINT_CREAR_USUARIO_APP)
	public ResponseEntity<EntityResponse<UsuarioDTO>> crearUsuarioApp(@Valid @RequestBody UsuarioDTO usuarioDTO) {
		try {

			UsuarioDTO nuevoUsuario = iAdminService.crearUsuarioApp(usuarioDTO);

			EntityResponse<UsuarioDTO> response = new EntityResponse<>();
			response.setSuccess(true);
			response.setData(nuevoUsuario);
			response.setMessage(Mensajes.TXT_CORREO_ENVIADO_OK);
			return ResponseEntity.ok(response);

		} catch (RegistroNoGuardadoException ex) {
			EntityResponse<UsuarioDTO> errorResponse = new EntityResponse<>();
			errorResponse.setSuccess(false);
			errorResponse.setMessage(ex.getMessage());
			return ResponseEntity.ok(errorResponse);
		} catch (Exception e) {
			EntityResponse<UsuarioDTO> errorResponse = new EntityResponse<>();
			errorResponse.setSuccess(false);
			errorResponse.setMessage(Mensajes.TXT_ERROR_APLICACION + e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
		}
	}
	
	@PostMapping(value = Constantes.ENDPOINT_CONSULTAR_USUARIO)
	public ResponseEntity<EntityResponse<List<User>>> consultarUsuario(@Valid @RequestBody UsuarioDTO usuarioDTO) {
        try {
        	List<User> usuariosList = iAdminService.consultarUsuario(usuarioDTO);
           
            EntityResponse<List<User>> response = new EntityResponse<>();
            response.setSuccess(true);
            response.setData(usuariosList);
            return ResponseEntity.ok(response);
            
        } catch (RegistroNoEncontradoException ex) {
        	EntityResponse<List<User>> errorResponse = new EntityResponse<>();
            errorResponse.setSuccess(false);
            errorResponse.setMessage(ex.getMessage());
            return ResponseEntity.ok(errorResponse);
        } catch (Exception e) {
        	EntityResponse<List<User>> errorResponse = new EntityResponse<>();
            errorResponse.setSuccess(false);
            errorResponse.setMessage(Mensajes.TXT_ERROR_APLICACION + e.getMessage());
        	return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
		}
    }
	
	@PostMapping(value = Constantes.ENDPOINT_CONSULTAR_USUARIO_BY_ID)
	public ResponseEntity<EntityResponse<User>> consultarUsuarioById(@Valid @RequestBody UsuarioDTO usuarioDTO) {
        try {
        	User usuarioEncontrado = iAdminService.consultarUsuarioById(usuarioDTO);
           
            EntityResponse<User> response = new EntityResponse<>();
            response.setSuccess(true);
            response.setData(usuarioEncontrado);
            return ResponseEntity.ok(response);
            
        } catch (RegistroNoEncontradoException ex) {
        	EntityResponse<User> errorResponse = new EntityResponse<>();
            errorResponse.setSuccess(false);
            errorResponse.setMessage(ex.getMessage());
            return ResponseEntity.ok(errorResponse);
        } catch (Exception e) {
        	EntityResponse<User> errorResponse = new EntityResponse<>();
            errorResponse.setSuccess(false);
            errorResponse.setMessage(Mensajes.TXT_ERROR_APLICACION + e.getMessage());
        	return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
		}
    }
	
	@PostMapping(value = Constantes.ENDPOINT_EDITAR_USUARIO)
    public ResponseEntity<EntityResponse<UsuarioDTO>> editarUsuario(@Valid @RequestBody UsuarioDTO usuarioDTO){
    	try {
    		
    		UsuarioDTO usuarioActualizado = iAdminService.actualizarUsuario(usuarioDTO);
    		
    		EntityResponse<UsuarioDTO> response = new EntityResponse<>();
            response.setSuccess(true);
            response.setData(usuarioActualizado);
            return ResponseEntity.ok(response);
			
		} catch (RegistroNoGuardadoException ex) {
			EntityResponse<UsuarioDTO> errorResponse = new EntityResponse<>();
            errorResponse.setSuccess(false);
            errorResponse.setMessage(ex.getMessage());
            return ResponseEntity.ok(errorResponse);
        } catch (Exception e) {
        	EntityResponse<UsuarioDTO> errorResponse = new EntityResponse<>();
            errorResponse.setSuccess(false);
            errorResponse.setMessage(Mensajes.TXT_ERROR_APLICACION + e.getMessage());
        	return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
		}
    }
	
	
	@PostMapping(value = Constantes.ENDPOINT_CAMBIO_PASSWORD_USUARIO_APP)
	public ResponseEntity<EntityResponse<PasswordDTO>> cambiarPassword(@Valid @RequestBody PasswordDTO passwordDTO) {	    
		EntityResponse<PasswordDTO> response = new EntityResponse<>();
	    try {
	        PasswordDTO cambioPwd = iAdminService.cambiarPasswordUsuario(passwordDTO);
	        
	        // Respuesta exitosa
	        response.setSuccess(true);
	        response.setData(cambioPwd);
	        response.setMessage("Contraseña cambiada con éxito.");
	        return ResponseEntity.ok(response);
	        
	    } catch (RegistroNoGuardadoException ex) {
	        // Respuesta en caso de error específico
	        response.setSuccess(false);
	        response.setMessage(ex.getMessage());
	        return ResponseEntity.badRequest().body(response); // 400 Bad Request
	        
	    } catch (PasswordMismatchException ex) {
	        EntityResponse<PasswordDTO> errorResponse = new EntityResponse<>();
	        errorResponse.setSuccess(false);
	        errorResponse.setMessage(ex.getMessage());
	        return ResponseEntity.badRequest().body(errorResponse); // Devuelve un error 400
	        
	    } catch (RegistroNoEncontradoException ex) {
	        // Usuario no encontrado
	        response.setSuccess(false);
	        response.setMessage("Usuario no encontrado.");
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response); // 404 Not Found
	        
	    } catch (Exception e) {
	        // Respuesta para errores generales
	        response.setSuccess(false);
	        response.setMessage(Mensajes.TXT_ERROR_APLICACION + e.getMessage());
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response); // 500 Internal Server Error
	    }
	}
}