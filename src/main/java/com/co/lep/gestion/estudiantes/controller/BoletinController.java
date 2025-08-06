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
import com.co.lep.gestion.estudiantes.dto.BoletinConsultaDTO;
import com.co.lep.gestion.estudiantes.dto.BoletinDTO;
import com.co.lep.gestion.estudiantes.entity.BoletinDetalleEntity;
import com.co.lep.gestion.estudiantes.entity.BoletinEntity;
import com.co.lep.gestion.estudiantes.entity.EntityResponse;
import com.co.lep.gestion.estudiantes.exepciones.RegistroNoEncontradoException;
import com.co.lep.gestion.estudiantes.exepciones.RegistroNoGuardadoException;
import com.co.lep.gestion.estudiantes.service.IBoletinService;

@RestController
@RequestMapping(Constantes.URL_BASE)
public class BoletinController {
	
	@Autowired
	IBoletinService iBoletinService;
	
	@PostMapping(value = Constantes.ENDPOINT_CONSULTAR_BOLETINES)
	public ResponseEntity<EntityResponse<List<BoletinEntity>>> consultarBoletines(@RequestBody BoletinConsultaDTO boletinDTO) {
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
	
	@PostMapping(value = Constantes.REQUEST_CONSULTAR_BOLETIN_BY_ID)
	public ResponseEntity<EntityResponse<BoletinEntity>> consultarBoletinesById(@RequestBody BoletinDTO boletinDTO) {
		try {
    		
    		BoletinEntity boletin = iBoletinService.consultarBoletinById(boletinDTO);
          
    		EntityResponse<BoletinEntity> response = new EntityResponse<>();
            response.setSuccess(true);
            response.setData(boletin);
            return ResponseEntity.ok(response);
			
		} catch (RegistroNoEncontradoException ex) {
			EntityResponse<BoletinEntity> errorResponse = new EntityResponse<>();
            errorResponse.setSuccess(false);
            errorResponse.setMessage(ex.getMessage());
            return ResponseEntity.ok(errorResponse);
        } catch (Exception e) {
        	EntityResponse<BoletinEntity> errorResponse = new EntityResponse<>();
            errorResponse.setSuccess(false);
            errorResponse.setMessage(Mensajes.TXT_ERROR_APLICACION + e.getMessage());
        	return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
		}
	}
	
	
	
	@PostMapping(value = Constantes.ENDPOINT_CONSULTAR_DETALLE_BOLETINES)
	public ResponseEntity<EntityResponse<List<BoletinDetalleEntity>>> consultarDetalleBoletines(@RequestBody BoletinDTO boletinDTO) {
		try {
    		
    		List<BoletinDetalleEntity> boletinesList = iBoletinService.consultarDetalleBoletines(boletinDTO);
          
    		EntityResponse<List<BoletinDetalleEntity>> response = new EntityResponse<>();
            response.setSuccess(true);
            response.setData(boletinesList);
            return ResponseEntity.ok(response);
			
		} catch (RegistroNoEncontradoException ex) {
			EntityResponse<List<BoletinDetalleEntity>> errorResponse = new EntityResponse<>();
            errorResponse.setSuccess(false);
            errorResponse.setMessage(ex.getMessage());
            return ResponseEntity.ok(errorResponse);
        } catch (Exception e) {
        	EntityResponse<List<BoletinDetalleEntity>> errorResponse = new EntityResponse<>();
            errorResponse.setSuccess(false);
            errorResponse.setMessage(Mensajes.TXT_ERROR_APLICACION + e.getMessage());
        	return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
		}
	}
	
	@GetMapping(value = Constantes.ENDPOINT_CONSULTAR_DETALLE_BOLETINES_BY_ID)
	public ResponseEntity<EntityResponse<List<BoletinDetalleEntity>>> consultarDetalleBoletinById(@RequestParam Long idBoletin) {
		try {
    		
    		List<BoletinDetalleEntity> boletinesList = iBoletinService.consultarDetalleBoletinById(idBoletin);
          
    		EntityResponse<List<BoletinDetalleEntity>> response = new EntityResponse<>();
            response.setSuccess(true);
            response.setData(boletinesList);
            return ResponseEntity.ok(response);
			
		} catch (RegistroNoEncontradoException ex) {
			EntityResponse<List<BoletinDetalleEntity>> errorResponse = new EntityResponse<>();
            errorResponse.setSuccess(false);
            errorResponse.setMessage(ex.getMessage());
            return ResponseEntity.ok(errorResponse);
        } catch (Exception e) {
        	EntityResponse<List<BoletinDetalleEntity>> errorResponse = new EntityResponse<>();
            errorResponse.setSuccess(false);
            errorResponse.setMessage(Mensajes.TXT_ERROR_APLICACION + e.getMessage());
        	return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
		}
	}
	
	@PostMapping(value = Constantes.ENDPOINT_CREAR_BOLETIN)
	public ResponseEntity<EntityResponse<Object>> crearBoletin(@RequestBody @Valid BoletinDTO boletinDTO) {
		
		try {
			
			BoletinDTO boletinCreado =  iBoletinService.crearBoletin(boletinDTO);
			
			EntityResponse<Object> response = new EntityResponse<>();
			response.setSuccess(true);
			response.setMessage(Mensajes.TXT_CREAR_REGISTRO_OK);
			response.setData(boletinCreado.getId());
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
	
	@PostMapping(value = Constantes.ENDPOINT_EDITAR_BOLETIN)
	public ResponseEntity<EntityResponse<Object>> editarBoletin(@RequestBody @Valid BoletinDTO boletinDTO) {
		
		try {
			
			iBoletinService.editarBoletin(boletinDTO);
			
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
	
	@PostMapping(value= Constantes.GENERAR_BOLETIN_PDF)
    public ResponseEntity<EntityResponse<byte[]>> convertirHtmlAPdf(@RequestBody Long boletinId){
    	try {
			
			byte[] boletinPDF = iBoletinService.generarBoletinPDF(boletinId);
			
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
	
	@PostMapping(value = Constantes.ENDPOINT_ELIMINAR_BOLETIN)
	public ResponseEntity<EntityResponse<Object>> eliminarBoletin(@Valid @RequestBody BoletinDTO boletinDTO) {
		try {

			iBoletinService.eliminarBoletin(boletinDTO.getId());

			EntityResponse<Object> response = new EntityResponse<>();
			response.setSuccess(true);
			response.setMessage(Mensajes.TXT_REGISTRO_ELIMINADO_OK);
			return ResponseEntity.ok(response);

		} catch (RegistroNoEncontradoException ex) {
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
