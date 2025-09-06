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
import com.co.lep.gestion.estudiantes.dto.DetalleEstudiantesNotaDTO;
import com.co.lep.gestion.estudiantes.dto.EstudiantesNotaDTO;
import com.co.lep.gestion.estudiantes.entity.DetalleEstudianteNotasEntity;
import com.co.lep.gestion.estudiantes.entity.EntityResponse;
import com.co.lep.gestion.estudiantes.entity.EstudianteNotasEntity;
import com.co.lep.gestion.estudiantes.exepciones.RegistroNoEncontradoException;
import com.co.lep.gestion.estudiantes.exepciones.RegistroNoGuardadoException;
import com.co.lep.gestion.estudiantes.service.IAdminService;
@RestController
@RequestMapping(Constantes.URL_BASE)
public class AdminEstudiantesNotaController {
	
	@Autowired
	IAdminService iAdminService;
	
	@PostMapping(value = Constantes.ENDPOINT_CONSULTAR_ESTUDIANTES_NOTA)
    public ResponseEntity<EntityResponse<List<EstudianteNotasEntity>>> consultarEstudiantesNota(@RequestBody EstudiantesNotaDTO estudiantesNotaDTO){
    	try {
    		
    		List<EstudianteNotasEntity> estudiantesList = iAdminService.consultarEstudiantesNota(estudiantesNotaDTO);
          
    		EntityResponse<List<EstudianteNotasEntity>> response = new EntityResponse<>();
            response.setSuccess(true);
            response.setData(estudiantesList);
            return ResponseEntity.ok(response);
			
		} catch (RegistroNoEncontradoException ex) {
			EntityResponse<List<EstudianteNotasEntity>> errorResponse = new EntityResponse<>();
            errorResponse.setSuccess(false);
            errorResponse.setMessage(ex.getMessage());
            return ResponseEntity.ok(errorResponse);
        } catch (Exception e) {
        	EntityResponse<List<EstudianteNotasEntity>> errorResponse = new EntityResponse<>();
            errorResponse.setSuccess(false);
            errorResponse.setMessage(Mensajes.TXT_ERROR_APLICACION + e.getMessage());
        	return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
		}
    }
    
    @PostMapping(value = Constantes.ENDPOINT_CONSULTAR_ESTUDIANTES_NOTA_BY_ID)
    public ResponseEntity<EntityResponse<List<DetalleEstudianteNotasEntity>>> consultarEstudiantesNotaById(@Valid @RequestBody DetalleEstudiantesNotaDTO detalleNotas){
    	try {
    		
    		List<DetalleEstudianteNotasEntity> estudiantes = iAdminService.consultarEstudiantesNotaById(detalleNotas);
         
    		EntityResponse<List<DetalleEstudianteNotasEntity>> response = new EntityResponse<>();
            response.setSuccess(true);
            response.setData(estudiantes);
            return ResponseEntity.ok(response);
			
		} catch (RegistroNoEncontradoException ex) {
			EntityResponse<List<DetalleEstudianteNotasEntity>> errorResponse = new EntityResponse<>();
            errorResponse.setSuccess(false);
            errorResponse.setMessage(ex.getMessage());
            return ResponseEntity.ok(errorResponse);
        } catch (Exception e) {
        	EntityResponse<List<DetalleEstudianteNotasEntity>> errorResponse = new EntityResponse<>();
            errorResponse.setSuccess(false);
            errorResponse.setMessage(Mensajes.TXT_ERROR_APLICACION + e.getMessage());
        	return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
		}
    }
    
    @PostMapping(value = Constantes.ENDPOINT_CREAR_ESTUDIANTES_NOTA)
    public ResponseEntity<EntityResponse<Object>> crearEstudiantesNota(@Valid @RequestBody EstudiantesNotaDTO estudiantesNotaDTO){
    	try {
    		
    		iAdminService.crearEstudiantesNota(estudiantesNotaDTO);
         
            EntityResponse<Object> response = new EntityResponse<>();
            response.setSuccess(true);
            response.setMessage(Mensajes.TXT_CREAR_REGISTRO_OK);
            return ResponseEntity.ok(response);
			
		} catch (RegistroNoGuardadoException ex) {
        	EntityResponse<Object> errorResponse = new EntityResponse<>();
            errorResponse.setSuccess(false);
            errorResponse.setMessage(ex.getMessage());
            return ResponseEntity.ok(errorResponse);
        } catch (Exception e) {
        	EntityResponse<Object> errorResponse = new EntityResponse<>();
            errorResponse.setSuccess(false);
            errorResponse.setMessage(Mensajes.TXT_ERROR_APLICACION + e.getMessage());
        	return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
		}
    }
    
    @PostMapping(value = Constantes.ENDPOINT_EDITAR_ESTUDIANTES_NOTA)
    public ResponseEntity<EntityResponse<Object>> editarEstudiantesNota(@Valid @RequestBody EstudiantesNotaDTO estudiantesNotaDTO){
    	try {
    		
            iAdminService.actualizarEstudiantesNota(estudiantesNotaDTO);
    		
    		EntityResponse<Object> response = new EntityResponse<>();
            response.setSuccess(true);
            response.setMessage(Mensajes.TXT_EDITAR_REGISTRO_OK);
            return ResponseEntity.ok(response);
			
		} catch (RegistroNoGuardadoException ex) {
			EntityResponse<Object> errorResponse = new EntityResponse<>();
            errorResponse.setSuccess(false);
            errorResponse.setMessage(ex.getMessage());
            return ResponseEntity.ok(errorResponse);
        } catch (Exception e) {
        	EntityResponse<Object> errorResponse = new EntityResponse<>();
            errorResponse.setSuccess(false);
            errorResponse.setMessage(Mensajes.TXT_ERROR_APLICACION + e.getMessage());
        	return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
		}
    }
}
