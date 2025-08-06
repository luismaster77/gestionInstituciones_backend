package com.co.lep.gestion.estudiantes.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.co.lep.gestion.estudiantes.constantes.Constantes;
import com.co.lep.gestion.estudiantes.constantes.Mensajes;
import com.co.lep.gestion.estudiantes.dto.DocenteDTO;
import com.co.lep.gestion.estudiantes.entity.DocenteEntity;
import com.co.lep.gestion.estudiantes.entity.EntityResponse;
import com.co.lep.gestion.estudiantes.exepciones.RegistroNoEncontradoException;
import com.co.lep.gestion.estudiantes.exepciones.RegistroNoGuardadoException;
import com.co.lep.gestion.estudiantes.service.IAdminService;


@RestController
@RequestMapping(Constantes.URL_BASE)
public class AdminDocentesController {
	
	@Autowired
	IAdminService iAdminService;
	
	
	@GetMapping(value = Constantes.ENDPOINT_CONSULTAR_DOCENTE_BY_CODIGO)
	public ResponseEntity<EntityResponse<DocenteDTO>> buscarDocente(@RequestParam String codDocente) {
        try {
            DocenteDTO docenteDTO = iAdminService.obtenerDocentePorCodigo(codDocente);
           
            EntityResponse<DocenteDTO> response = new EntityResponse<>();
            response.setSuccess(true);
            response.setData(docenteDTO);
            return ResponseEntity.ok(response);
            
        } catch (RegistroNoEncontradoException ex) {
        	EntityResponse<DocenteDTO> errorResponse = new EntityResponse<>();
            errorResponse.setSuccess(false);
            errorResponse.setMessage(ex.getMessage());
            return ResponseEntity.ok(errorResponse);
        } catch (Exception e) {
        	EntityResponse<DocenteDTO> errorResponse = new EntityResponse<>();
            errorResponse.setSuccess(false);
            errorResponse.setMessage(Mensajes.TXT_ERROR_APLICACION + e.getMessage());
        	return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
		}
    }
	
	@PostMapping(value = Constantes.ENDPOINT_CONSULTAR_DOCENTE)
	public ResponseEntity<EntityResponse<List<DocenteEntity>>> consultarDocente(@Valid @RequestBody DocenteDTO docenteDTO) {
        try {
        	List<DocenteEntity> docentesList = iAdminService.consultarDocente(docenteDTO);
           
            EntityResponse<List<DocenteEntity>> response = new EntityResponse<>();
            response.setSuccess(true);
            response.setData(docentesList);
            return ResponseEntity.ok(response);
            
        } catch (RegistroNoEncontradoException ex) {
        	EntityResponse<List<DocenteEntity>> errorResponse = new EntityResponse<>();
            errorResponse.setSuccess(false);
            errorResponse.setMessage(ex.getMessage());
            return ResponseEntity.ok(errorResponse);
        } catch (Exception e) {
        	EntityResponse<List<DocenteEntity>> errorResponse = new EntityResponse<>();
            errorResponse.setSuccess(false);
            errorResponse.setMessage(Mensajes.TXT_ERROR_APLICACION + e.getMessage());
        	return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
		}
    }
	
	@PostMapping(value = Constantes.ENDPOINT_CONSULTAR_DOCENTE_BY_ID)
	public ResponseEntity<EntityResponse<DocenteEntity>> consultarDocenteById(@Valid @RequestBody DocenteDTO docenteDTO) {
        try {
        	DocenteEntity docenteEncontrado = iAdminService.consultarDocenteById(docenteDTO);
           
            EntityResponse<DocenteEntity> response = new EntityResponse<>();
            response.setSuccess(true);
            response.setData(docenteEncontrado);
            return ResponseEntity.ok(response);
            
        } catch (RegistroNoEncontradoException ex) {
        	EntityResponse<DocenteEntity> errorResponse = new EntityResponse<>();
            errorResponse.setSuccess(false);
            errorResponse.setMessage(ex.getMessage());
            return ResponseEntity.ok(errorResponse);
        } catch (Exception e) {
        	EntityResponse<DocenteEntity> errorResponse = new EntityResponse<>();
            errorResponse.setSuccess(false);
            errorResponse.setMessage(Mensajes.TXT_ERROR_APLICACION + e.getMessage());
        	return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
		}
    }
	
	@GetMapping(value = Constantes.ENDPOINT_CONSULTAR_DOCENTES_ALL)
	public ResponseEntity<EntityResponse<List<DocenteEntity>>> consultarDocentes() {
        try {
            List<DocenteEntity> docentesList = iAdminService.consultarDocentes();
           
            EntityResponse<List<DocenteEntity>> response = new EntityResponse<>();
            response.setSuccess(true);
            response.setData(docentesList);
            return ResponseEntity.ok(response);
            
        } catch (RegistroNoEncontradoException ex) {
        	EntityResponse<List<DocenteEntity>> errorResponse = new EntityResponse<>();
            errorResponse.setSuccess(false);
            errorResponse.setMessage(ex.getMessage());
            return ResponseEntity.ok(errorResponse);
        } catch (Exception e) {
        	EntityResponse<List<DocenteEntity>> errorResponse = new EntityResponse<>();
            errorResponse.setSuccess(false);
            errorResponse.setMessage(Mensajes.TXT_ERROR_APLICACION + e.getMessage());
        	return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
		}
    }
	
	@PostMapping(value = Constantes.ENDPOINT_CREAR_DOCENTE)
    public ResponseEntity<EntityResponse<Object>> crearCuentaDocente(@Valid @RequestBody DocenteDTO docenteDTO){
    	try {
    		
    		iAdminService.crearCuentaDocente(docenteDTO);
         
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
	
	@PostMapping(value = Constantes.ENDPOINT_EDITAR_DOCENTE)
    public ResponseEntity<EntityResponse<Object>> editarDocente(@Valid @RequestBody DocenteDTO docenteDTO){
    	try {
    		
            iAdminService.actualizarDocente(docenteDTO);
    		
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
