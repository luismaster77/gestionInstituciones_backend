package com.co.flexicraftsolutions.gestion.estudiantes.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.co.flexicraftsolutions.gestion.estudiantes.constantes.Constantes;
import com.co.flexicraftsolutions.gestion.estudiantes.constantes.Mensajes;
import com.co.flexicraftsolutions.gestion.estudiantes.dto.BoletinDTO;
import com.co.flexicraftsolutions.gestion.estudiantes.entity.BoletinEntity;
import com.co.flexicraftsolutions.gestion.estudiantes.entity.EntityResponse;
import com.co.flexicraftsolutions.gestion.estudiantes.exepciones.RegistroNoEncontradoException;
import com.co.flexicraftsolutions.gestion.estudiantes.exepciones.RegistroNoGuardadoException;
import com.co.flexicraftsolutions.gestion.estudiantes.service.IBoletinService;

@RestController
@RequestMapping(Constantes.URL_BASE)
public class BoletinController {
	
	@Autowired
	IBoletinService iBoletinService;
	
	@PostMapping(value = Constantes.ENDPOINT_CONSULTAR_BOLETINES)
	public ResponseEntity<EntityResponse<List<BoletinEntity>>> consultarBoletines(@RequestBody BoletinDTO boletinDTO) {
		try {
    		
    		List<BoletinEntity> boletinesList = iBoletinService.consultarBoletines(boletinDTO);
          
    		EntityResponse<List<BoletinEntity>> response = new EntityResponse<>();
            response.setSuccess(true);
            response.setData(boletinesList);
            return ResponseEntity.ok(response);
			
		} catch (RegistroNoEncontradoException ex) {
			EntityResponse<List<BoletinEntity>> errorResponse = new EntityResponse<>();
            errorResponse.setSuccess(false);
            errorResponse.setMessage(ex.getMessage());
            return ResponseEntity.ok(errorResponse);
        } catch (Exception e) {
        	EntityResponse<List<BoletinEntity>> errorResponse = new EntityResponse<>();
            errorResponse.setSuccess(false);
            errorResponse.setMessage(Mensajes.TXT_ERROR_APLICACION + e.getMessage());
        	return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
		}
	}
	
	@PostMapping(value = Constantes.ENDPOINT_CREAR_BOLETIN)
	public ResponseEntity<EntityResponse<BoletinDTO>> crearBoletin(@RequestBody BoletinDTO boletinDTO) {
		try {
			
			BoletinDTO nuevoBoletin = iBoletinService.crearBoletin(boletinDTO);
			
			EntityResponse<BoletinDTO> response = new EntityResponse<>();
			response.setSuccess(true);
			response.setData(nuevoBoletin);
			return ResponseEntity.ok(response);
			
		} catch (RegistroNoGuardadoException ex) {
	    	EntityResponse<BoletinDTO> errorResponse = new EntityResponse<>();
	        errorResponse.setSuccess(false);
	        errorResponse.setMessage(ex.getMessage());
	        return ResponseEntity.ok(errorResponse);
	    } catch (Exception e) {
	    	EntityResponse<BoletinDTO> errorResponse = new EntityResponse<>();
	        errorResponse.setSuccess(false);
	        errorResponse.setMessage(Mensajes.TXT_ERROR_APLICACION + e.getMessage());
	    	return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
		}
	}
	
	@PostMapping(value= Constantes.GENERAR_BOLETIN_PDF)
    public ResponseEntity<EntityResponse<byte[]>> convertirHtmlAPdf(@RequestBody BoletinDTO boletinDTO){
    	try {
			
			byte[] boletinPDF = iBoletinService.generarBoletinPDF(boletinDTO);
			
			EntityResponse<byte[]> response = new EntityResponse<>();
	        
			response.setSuccess(true);
			response.setData(boletinPDF);
			return ResponseEntity.ok(response);
			
		} catch (RegistroNoGuardadoException ex) {
	    	EntityResponse<byte[]> errorResponse = new EntityResponse<>();
	        errorResponse.setSuccess(false);
	        errorResponse.setMessage(ex.getMessage());
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
	    } catch (Exception e) {
	    	EntityResponse<byte[]> errorResponse = new EntityResponse<>();
	        errorResponse.setSuccess(false);
	        errorResponse.setMessage(Mensajes.TXT_ERROR_APLICACION + e.getMessage());
	    	return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
		}
    }
}
