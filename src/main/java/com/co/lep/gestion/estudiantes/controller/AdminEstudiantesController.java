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
import com.co.lep.gestion.estudiantes.dto.EstudianteDTO;
import com.co.lep.gestion.estudiantes.entity.EntityResponse;
import com.co.lep.gestion.estudiantes.entity.EstudianteEntity;
import com.co.lep.gestion.estudiantes.exepciones.RegistroNoEncontradoException;
import com.co.lep.gestion.estudiantes.exepciones.RegistroNoGuardadoException;
import com.co.lep.gestion.estudiantes.service.IAdminService;
@RestController
@RequestMapping(Constantes.URL_BASE)
public class AdminEstudiantesController {
	
	@Autowired
	IAdminService iAdminService;
	
	@PostMapping(value = Constantes.ENDPOINT_CONSULTAR_ESTUDIANTE)
    public ResponseEntity<EntityResponse<List<EstudianteEntity>>> consultarEstudiante(@RequestBody EstudianteDTO estudianteDTO){
    	try {
    		
    		List<EstudianteEntity> estudiantesList = iAdminService.consultarEstudiante(estudianteDTO);
          
    		EntityResponse<List<EstudianteEntity>> response = new EntityResponse<>();
            response.setSuccess(true);
            response.setData(estudiantesList);
            return ResponseEntity.ok(response);
			
		} catch (RegistroNoEncontradoException ex) {
			EntityResponse<List<EstudianteEntity>> errorResponse = new EntityResponse<>();
            errorResponse.setSuccess(false);
            errorResponse.setMessage(ex.getMessage());
            return ResponseEntity.ok(errorResponse);
        } catch (Exception e) {
        	EntityResponse<List<EstudianteEntity>> errorResponse = new EntityResponse<>();
            errorResponse.setSuccess(false);
            errorResponse.setMessage(Mensajes.TXT_ERROR_APLICACION + e.getMessage());
        	return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
		}
    }
    
    @PostMapping(value = Constantes.ENDPOINT_CONSULTAR_ESTUDIANTE_BY_ID)
    public ResponseEntity<EntityResponse<EstudianteEntity>> consultarEstudianteById(@Valid @RequestBody EstudianteDTO estudianteDTO){
    	try {
    		
    		EstudianteEntity estudiantes = iAdminService.consultarEstudianteById(estudianteDTO);
         
    		EntityResponse<EstudianteEntity> response = new EntityResponse<>();
            response.setSuccess(true);
            response.setData(estudiantes);
            return ResponseEntity.ok(response);
			
		} catch (RegistroNoEncontradoException ex) {
			EntityResponse<EstudianteEntity> errorResponse = new EntityResponse<>();
            errorResponse.setSuccess(false);
            errorResponse.setMessage(ex.getMessage());
            return ResponseEntity.ok(errorResponse);
        } catch (Exception e) {
        	EntityResponse<EstudianteEntity> errorResponse = new EntityResponse<>();
            errorResponse.setSuccess(false);
            errorResponse.setMessage(Mensajes.TXT_ERROR_APLICACION + e.getMessage());
        	return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
		}
    }
    
    @PostMapping(value = Constantes.ENDPOINT_CREAR_ESTUDIANTE)
    public ResponseEntity<EntityResponse<Object>> crearEstudiante(@Valid @RequestBody EstudianteDTO estudianteDTO){
    	try {
    		
    		iAdminService.crearEstudiante(estudianteDTO);
         
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
    
    @PostMapping(value = Constantes.ENDPOINT_EDITAR_ESTUDIANTE)
    public ResponseEntity<EntityResponse<Object>> editarEstudiante(@Valid @RequestBody EstudianteDTO estudianteDTO){
    	try {
    		
            iAdminService.actualizarEstudiante(estudianteDTO);
    		
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
